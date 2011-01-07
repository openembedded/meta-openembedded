#!/bin/sh -e

load_input_modules() {
  for module in mousedev evdev joydev; do
    modprobe -q $module || true
  done
}

if [ ! -e /sys/class/mem/null/uevent ]; then # <= 2.6.14
  /lib/udev/udevsynthesize
  load_input_modules
  exit 0
fi

# replace $IFS with something which is not likely to appear in a sysfs path,
# because some buggy drivers have spaces in their names
oldifs="$IFS"
IFS="|"

for file in /sys/bus/*/devices/*/uevent /sys/class/*/*/uevent \
		/sys/block/*/uevent /sys/block/*/*/uevent; do
  case "$file" in
    */device/uevent) ;;		# skip followed device symlinks
    */\*/*) ;;

    */class/mem/*)		# for /dev/null
    first="$first${IFS}$file" ;;

    */block/md[0-9]*)
    last="$last${IFS}$file" ;;

    *)
    default="$default${IFS}$file" ;;
  esac
done

for file in $first${IFS}$default${IFS}$last; do
  [ "$file" ] || continue
  echo 'add' > "$file" || true
done

IFS="$oldifs"

case "$(uname -r)" in
  2.6.1[0-5]|2.6.1[0-5][!0-9]*) # <= 2.6.15
  load_input_modules
  ;;
esac

exit 0

