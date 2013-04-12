PRINC := "${@int(PRINC) + 2}"

inherit systemd

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "avahi-daemon.service"
SYSTEMD_AUTO_ENABLE = "enable"

FILES_${PN} += "${systemd_unitdir}/system/"
