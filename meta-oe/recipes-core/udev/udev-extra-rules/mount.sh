#!/bin/sh
#
# Called from udev
# Attemp to mount any added block devices
# and remove any removed devices
#

MOUNT="/bin/mount"
PMOUNT="/usr/bin/pmount"
UMOUNT="/bin/umount"
name="`basename "$DEVNAME"`"

for line in `grep -v ^# /etc/udev/mount.blacklist`
do
	if ( echo "$DEVNAME" | grep -q "$line" )
	then
		logger "udev/mount.sh" "[$DEVNAME] is blacklisted, ignoring"
		exit 0
	fi
done

automount() {
	! test -d "/media/$name" && mkdir -p "/media/$name"

	if ! $MOUNT -t auto -o async,relatime $DEVNAME "/media/$name"
	then
		#logger "mount.sh/automount" "$MOUNT -t auto $DEVNAME \"/media/$name\" failed!"
		rm_dir "/media/$name"
	else
		logger "mount.sh/automount" "Auto-mount of [/media/$name] successful"
		touch "/tmp/.automount-$name"
	fi
}

rm_dir() {
	# We do not want to rm -r populated directories
	if test "`find "$1" | wc -l | tr -d " "`" -lt 2 -a -d "$1"
	then
		! test -z "$1" && rm -r "$1"
	else
		logger "mount.sh/automount" "Not removing non-empty directory [$1]"
	fi
}

if [ "$ACTION" = "add" ] && [ -n "$DEVNAME" ]; then
	if [ -x "$PMOUNT" ]; then
		$PMOUNT $DEVNAME 2> /dev/null
	elif [ -x $MOUNT ]; then
			$MOUNT $DEVNAME 2> /dev/null
	fi

	# Avoid remounting devices (e.g: rootfs)
	awk '{print $1}' /proc/mounts | grep -q "^$DEVNAME$" || automount
fi

if [ "$ACTION" = "remove" ] && [ -x "$UMOUNT" ] && [ -n "$DEVNAME" ]; then
	for mnt in `grep "$DEVNAME" /proc/mounts | cut -f 2 -d " " `
	do
		$UMOUNT -l $mnt
	done

	# Remove empty directories from auto-mounter
	test -e "/tmp/.automount-$name" && rm_dir "/media/$name"
fi
