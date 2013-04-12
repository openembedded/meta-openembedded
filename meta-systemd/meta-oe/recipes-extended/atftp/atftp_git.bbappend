FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 2}"

inherit systemd

SRC_URI += "file://atftpd.service"

SYSTEMD_PACKAGES = "${PN}d"
SYSTEMD_SERVICE_${PN}d = "atftpd.service"
FILES_${PN}d += "${systemd_unitdir}/system/atftpd.service"
RPROVIDES_${PN}d += "${PN}d-systemd"
RREPLACES_${PN}d += "${PN}d-systemd"
RCONFLICTS_${PN}d += "${PN}d-systemd"
do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/atftpd.service ${D}${systemd_unitdir}/system
}

