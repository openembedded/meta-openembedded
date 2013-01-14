FILESEXTRAPATHS := "${THISDIR}/${PN}"

PRINC := "${@int(PRINC) + 1}"

inherit systemd

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "portmap.service"
SYSTEMD_AUTO_ENABLE = "disable"

SRC_URI_append = " file://portmap.service"
