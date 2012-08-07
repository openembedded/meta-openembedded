# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 2}"

inherit systemd

SRC_URI += " \
            file://gdm.service \
           "

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "gdm.service"
