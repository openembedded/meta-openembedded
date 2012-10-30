inherit systemd

PRINC := "${@int(PRINC) + 1}"

# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://atd.service"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "atd.service"

