FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 2}"

inherit systemd

SRC_URI += "file://atftpd.service"

SYSTEMD_PACKAGES = "atftpd"
SYSTEMD_SERVICE_atftpd = "atftpd.service"
FILES_atftpd += "${systemd_unitdir}/system/atftpd.service"
RPROVIDES_atftpd += "atftpd-systemd"
RREPLACES_atftpd += "atftpd-systemd"
RCONFLICTS_atftpd += "atftpd-systemd"
do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/atftpd.service ${D}${systemd_unitdir}/system
}

