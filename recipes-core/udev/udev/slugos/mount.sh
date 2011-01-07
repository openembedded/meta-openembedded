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

if ( blkid "$DEVNAME" | grep -q 'TYPE="mdraid"' )
then
	logger "udev/mount.sh" "[$DEVNAME] is a member of an array, ignoring"
	exit 0
fi

for line in `cat /etc/udev/mount.blacklist | grep -v ^#`
do
	if ( echo "$DEVNAME" | grep -q "$line" )
	then
		logger "udev/mount.sh" "[$DEVNAME] is blacklisted, ignoring"
		exit 0
	fi
done

automount() {	
	! test -d "/media/$name" && mkdir -p "/media/$name"
	
	if ! $MOUNT -t auto -o sync $DEVNAME "/media/$name"
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
	# SlugOS: we pivot to the rootfs based on UUID, not on fstab -- so the fstab may not
	# be correct at this point in the boot.  So we must not let udev mount devices based
	# soley on the fstab, lest we mount overtop the real rootfs.  For now we just comment
	# out the logic below and let the automount logic (far below) deal with all udev mount
	# operations.
	#if [ -x "$PMOUNT" ]; then
	#	$PMOUNT $DEVNAME 2> /dev/null
	#elif [ -x $MOUNT ]; then
	#	$MOUNT $DEVNAME 2> /dev/null
	#fi
	
	# If the device isn't mounted at this point, it isn't configured in fstab
	# 20061107: Small correction: The rootfs partition may be called just "rootfs" and not by
	# 	    its true device name so this would break. If the rootfs is mounted on two places
	#	    during boot, it confuses the heck out of fsck. So Im auto-adding the root-partition
	#	    to /etc/udev/mount.blacklist via postinst 

	cat /proc/mounts | awk '{print $1}' | grep -q "^$DEVNAME$" || automount 
	
fi



if [ "$ACTION" = "remove" ] && [ -x "$UMOUNT" ] && [ -n "$DEVNAME" ]; then
	for mnt in `cat /proc/mounts | grep "$DEVNAME" | cut -f 2 -d " " `
	do
		$UMOUNT -l $mnt
	done
	
	# Remove empty directories from auto-mounter
	test -e "/tmp/.automount-$name" && rm_dir "/media/$name"
fi
