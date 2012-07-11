FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 1}"

inherit systemd

SRC_URI += "file://xserver-nodm.service \
            file://xserver-nodm.conf \
"

do_install_append() {
        install -d ${D}${sysconfdir}/default
        install xserver-nodm.conf ${D}${sysconfdir}/default/xserver-nodm
}

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "xserver-nodm.service"

FILES_${PN}-systemd += "${sysconfdir}/default/xserver-nodm"

RDEPENDS_${PN}-systemd += "xserver-common (>= 1.30) xinit"
