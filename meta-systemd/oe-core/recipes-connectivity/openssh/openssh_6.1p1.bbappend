FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 2}"

SYSTEMD_PACKAGES = "openssh-sshd"
SYSTEMD_SERVICE_openssh-sshd = "sshd.socket"
FILES_openssh-sshd += "${systemd_unitdir}/system/sshd.socket"
RPROVIDES_openssh-sshd += "openssh-sshd-systemd"
RREPLACES_openssh-sshd += "openssh-sshd-systemd"
RCONFLICTS_openssh-sshd += "openssh-sshd-systemd"

inherit systemd

SRC_URI += "file://sshd.socket file://sshd@.service file://sshdgenkeys.service"
do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/sshd.socket ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/sshd@.service ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/sshdgenkeys.service ${D}${systemd_unitdir}/system
}

