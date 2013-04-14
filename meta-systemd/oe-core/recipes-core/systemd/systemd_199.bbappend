PRINC := "${@int(PRINC) + 1}"

RPROVIDES_udev += "udev-systemd"
RREPLACES_udev += "udev-systemd"
RCONFLICTS_udev += "udev-systemd"
