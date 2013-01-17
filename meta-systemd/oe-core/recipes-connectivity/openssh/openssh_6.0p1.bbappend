FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 3}"

SYSTEMD_PACKAGES = "openssh-sshd-systemd"
SYSTEMD_SERVICE = "sshd.socket"

inherit systemd

SRC_URI += "file://sshd.socket file://sshd@.service file://sshdgenkeys.service"
