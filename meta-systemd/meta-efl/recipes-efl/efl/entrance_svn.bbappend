FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 3}"

inherit systemd

SRC_URI += "file://entrance.service"

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"

SYSTEMD_SERVICE_${PN} = "entrance.service"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/entrance.service ${D}${systemd_unitdir}/system
}

RCONFLICTS_${PN} += "xserver-nodm-init-systemd"
RREPLACES_${PN} += "xserver-nodm-init-systemd"
