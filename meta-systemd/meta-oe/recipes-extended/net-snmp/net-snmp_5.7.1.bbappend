FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 1}"

inherit systemd

SRC_URI += " \
        file://systemd-support.patch \
        file://snmpd.service \
        file://snmptrapd.service \
"
do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/snmpd.service ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/snmptrapd.service ${D}${systemd_unitdir}/system
}

EXTRA_OECONF += "--with-systemd"

SYSTEMD_PACKAGES = "${PN}-server-snmpd-systemd \
                    ${PN}-server-snmptrapd-systemd"

SYSTEMD_SERVICE_${PN}-server-snmpd = "snmpd.service"
SYSTEMD_SERVICE_${PN}-server-snmptrapd =  "snmptrapd.service"
RPROVIDES_${PN}-server-snmpd += "${PN}-server-snmpd-systemd"
RPROVIDES_${PN}-server-snmptrapd += "${PN}-server-snmptrapd-systemd"
FILES_${PN}-server-snmpd += "${systemd_unitdir}/system/snmpd.service"
FILES_${PN}-server-snmptrapd += "${systemd_unitdir}/system/snmptrapd.service"
