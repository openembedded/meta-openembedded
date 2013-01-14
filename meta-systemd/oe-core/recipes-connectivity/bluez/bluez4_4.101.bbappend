PRINC := "${@int(PRINC) + 1}"

inherit systemd

EXTRA_OECONF += "--with-systemdunitdir=${systemd_unitdir}/system/"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "bluetooth.service"
SYSTEMD_AUTO_ENABLE = "disable"
