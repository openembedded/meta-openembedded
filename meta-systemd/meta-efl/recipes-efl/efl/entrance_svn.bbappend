FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 1}"

inherit systemd

SRC_URI += "file://entrance.service"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "entrance.service"

RCONFLICTS_${PN}-systemd += "xserver-nodm-init-systemd"
RREPLACES_${PN}-systemd += "xserver-nodm-init-systemd"
