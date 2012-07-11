PRINC := "${@int(PRINC) + 1}"

inherit systemd

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://gpsd.service"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "${PN}.socket"
SYSTEMD_OESCONS = "true"

do_install_append() {
    #support for systemd
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/${PN}.service ${D}${systemd_unitdir}/system/${PN}.service
    install -m 0644 ${S}/systemd/${PN}.socket ${D}${systemd_unitdir}/system/${PN}.socket
}
