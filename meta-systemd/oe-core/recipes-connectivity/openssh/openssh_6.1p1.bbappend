FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 3}"

SYSTEMD_PACKAGES = "${PN}-sshd"
SYSTEMD_SERVICE_${PN}-sshd = "sshd.socket"
FILES_${PN}-sshd += "${systemd_unitdir}/system/sshd.socket"
RPROVIDES_${PN}-sshd += "${PN}-sshd-systemd"
RREPLACES_${PN}-sshd += "${PN}-sshd-systemd"
RCONFLICTS_${PN}-sshd += "${PN}-sshd-systemd"

inherit systemd

SRC_URI += "file://sshd.socket file://sshd@.service file://sshdgenkeys.service"
do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/sshd.socket ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/sshd@.service ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/sshdgenkeys.service ${D}${systemd_unitdir}/system
}

