FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 2}"

inherit systemd

SRC_URI += "file://lighttpd.service"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "lighttpd.service"
