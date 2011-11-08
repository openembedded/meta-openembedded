# look for files in the layer first
FILESEXTRAPATHS := "${THISDIR}/${PN}"

PRINC = "1"

inherit systemd

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "crond.service"

SRC_URI += "file://crond.service"

do_install_append() {
    install -d ${D}${base_libdir}/systemd/system
    install -m 644 ${WORKDIR}/crond.service ${D}${base_libdir}/systemd/system
}

PACKAGES =+ "${PN}-systemd"

FILES_${PN}-systemd += "${base_libdir}/systemd"
RDEPENDS_${PN}-systemd = "${PN}"
