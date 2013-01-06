FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 1}"

inherit systemd

SRC_URI += "file://atftpd.service"

SYSTEMD_PACKAGES = "atftpd-systemd"
SYSTEMD_SERVICE_atftpd-systemd = "atftpd.service"
