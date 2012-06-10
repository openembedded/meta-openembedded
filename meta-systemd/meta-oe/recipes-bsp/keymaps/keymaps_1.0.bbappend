FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 6}"

SRC_URI += " file://keymaps.service"

inherit systemd
SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "${PN}.service"
