PRINC := "${@int(PRINC) + 1}"

inherit systemd

EXTRA_OECONF += "--with-systemdunitdir=${systemd_unitdir}/system/"

RPROVIDES_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "bluetooth.service"
SYSTEMD_AUTO_ENABLE = "disable"
