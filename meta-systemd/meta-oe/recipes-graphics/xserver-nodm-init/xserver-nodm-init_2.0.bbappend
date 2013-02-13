FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 2}"

inherit systemd

SRC_URI += "file://xserver-nodm.service \
            file://xserver-nodm.conf \
"

do_install_append() {
        install -d ${D}${sysconfdir}/default
	install -d ${D}${systemd_unitdir}/system
        install xserver-nodm.conf ${D}${sysconfdir}/default/xserver-nodm
	install -m 0644 ${WORKDIR}/xserver-nodm.service ${D}${systemd_unitdir}/system
}

SYSTEMD_PACKAGES = "${PN}"
RPROVIDES_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "xserver-nodm.service"

FILES_${PN} += "${sysconfdir}/default/xserver-nodm"

RDEPENDS_${PN} += "xserver-common (>= 1.30) xinit"
