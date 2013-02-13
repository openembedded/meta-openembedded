PRINC := "${@int(PRINC) + 1}"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
RPROVIDES_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "avahi-daemon.service"
SYSTEMD_AUTO_ENABLE = "enable"

FILES_${PN} += "${systemd_unitdir}/system/"
