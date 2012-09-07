FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 1}"

inherit systemd

SRC_URI += "file://dnsmasq.service"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "dnsmasq.service"
