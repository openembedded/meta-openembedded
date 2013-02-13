# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 3}"

inherit systemd

SRC_URI += "file://gdm.service.in"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	sed -e 's,%sbindir%,${sbindir},g' \
		< ${WORKDIR}/gdm.service.in \
		> ${D}${systemd_unitdir}/system/gdm.service
}

SYSTEMD_PACKAGES = "${PN}"
RPROVIDES_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "gdm.service"
