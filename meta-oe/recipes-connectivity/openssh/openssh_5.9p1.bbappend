FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 1}"

SYSTEMD_PACKAGES = "openssh-sshd-systemd"
SYSTEMD_SERVICE = "sshd.socket"

inherit systemd

SRC_URI += "file://sshd.socket file://sshd@.service file://sshdgenkeys.service"

do_install_append() {
       install -d ${D}${base_libdir}/systemd/system
       install -m 644 ${WORKDIR}/sshd.socket ${D}${base_libdir}/systemd/system
       install -m 644 ${WORKDIR}/sshd@.service ${D}${base_libdir}/systemd/system
       install -m 644 ${WORKDIR}/sshdgenkeys.service ${D}${base_libdir}/systemd/system
}

PACKAGES =+ "openssh-sshd-systemd"

FILES_openssh-sshd-systemd += "${base_libdir}/systemd"
RDEPENDS_openssh-sshd-systemd += "openssh-sshd"
