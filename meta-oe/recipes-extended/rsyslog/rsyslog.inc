DESCRIPTION = "Rsyslog is an enhanced multi-threaded syslogd"
DEPENDS = "zlib systemd"
HOMEPAGE = "http://www.rsyslog.com/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=51d9635e646fb75e1b74c074f788e973"

INC_PR = "r0"

SRC_URI = "http://www.rsyslog.com/files/download/rsyslog/${PN}-${PV}.tar.gz \
           file://rsyslog.conf"
           

inherit autotools

do_install_append() {
        install -d ${D}/${sysconfdir}/${PN}
        install ${WORKDIR}/rsyslog.conf ${D}${sysconfdir}/rsyslog.conf
}


pkg_postinst_${PN} () {
	if test "x$D" != "x"; then
		exit 1
	fi
	systemctl enable rsyslog.service
}

pkg_postrm_${PN} () {
	systemctl disable rsyslog.service
}

RRECOMMENDS_${PN} += "systemd"
CONFFILES_${PN} = "${sysconfdir}/rsyslog.conf"
FILES_${PN} += "${base_libdir}/systemd"

