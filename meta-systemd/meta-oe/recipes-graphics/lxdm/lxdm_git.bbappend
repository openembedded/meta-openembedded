FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 1}"

inherit systemd

SRC_URI += "file://lxdm.service.in"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	sed -e 's,%sbindir%,${sbindir},g' \
		< ${WORKDIR}/lxdm.service.in \
		> ${D}${systemd_unitdir}/system/lxdm.service
}

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "lxdm.service"
