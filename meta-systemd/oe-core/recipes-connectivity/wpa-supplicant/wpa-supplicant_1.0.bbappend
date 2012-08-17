PRINC = "2"

inherit systemd

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "wpa_supplicant.service"
SYSTEMD_AUTO_ENABLE = "disable"

do_install_append () {
    install -d ${D}${systemd_unitdir}/system
    install -m 644 ${S}/systemd/*.service ${D}${systemd_unitdir}/system
}

# systemd.bbclass does not catch all multiple-instance-service-files
FILES_${PN}-systemd += "${systemd_unitdir}/system/"
