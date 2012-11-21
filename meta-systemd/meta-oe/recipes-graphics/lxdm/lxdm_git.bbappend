FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 1}"

inherit systemd

SYSTEMD_UNITDIR = "${systemd_unitdir}/system"
SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "lxdm.service"
