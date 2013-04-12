FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 2}"

inherit systemd

SRC_URI += "file://gateone.service"

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_SERVICE_${PN} = "gateone.service"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/gateone.service ${D}${systemd_unitdir}/system
}

