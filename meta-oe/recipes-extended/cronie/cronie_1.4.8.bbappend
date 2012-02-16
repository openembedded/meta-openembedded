# look for files in the layer first
FILESEXTRAPATHS := "${THISDIR}/${PN}"

PRINC = "2"

inherit systemd

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "crond.service"

SRC_URI += "file://crond.service"
