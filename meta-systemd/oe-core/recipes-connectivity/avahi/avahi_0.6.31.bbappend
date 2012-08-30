PRINC := "${@int(PRINC) + 1}"

inherit systemd

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "avahi-daemon.service"
SYSTEMD_AUTO_ENABLE = "enable"

FILES_${PN}-systemd += "${systemd_unitdir}/system/"
