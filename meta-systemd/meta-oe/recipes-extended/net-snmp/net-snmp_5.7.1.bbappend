FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 1}"

inherit systemd

SRC_URI += " \
        file://systemd-support.patch \
        file://snmpd.service \
        file://snmptrapd.service \
"

EXTRA_OECONF += "--with-systemd"

SYSTEMD_PACKAGES = "${PN}-server-snmpd-systemd \
                    ${PN}-server-snmptrapd-systemd"

SYSTEMD_SERVICE_${PN}-server-snmpd-systemd = "snmpd.service"
SYSTEMD_SERVICE_${PN}-server-snmptrapd-systemd =  "snmptrapd.service"
