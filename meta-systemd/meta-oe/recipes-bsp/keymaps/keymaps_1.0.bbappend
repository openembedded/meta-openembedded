FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 6}"

SRC_URI += " file://keymaps.service"

inherit systemd
SYSTEMD_PACKAGES = "${PN}"
RPROVIDES_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "${PN}.service"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/${PN}.service ${D}${systemd_unitdir}/system
}

