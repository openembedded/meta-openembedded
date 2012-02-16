DESCRIPTION = "Rsyslog is an enhanced multi-threaded syslogd"
DEPENDS = "zlib systemd"
HOMEPAGE = "http://www.rsyslog.com/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=51d9635e646fb75e1b74c074f788e973"

INC_PR = "r3"

SRC_URI = "http://www.rsyslog.com/files/download/rsyslog/${PN}-${PV}.tar.gz \
           file://rsyslog.conf"
           

inherit autotools systemd

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "${PN}.service"

do_install_append() {
        install -d ${D}/${sysconfdir}/${PN}
        install ${WORKDIR}/rsyslog.conf ${D}${sysconfdir}/rsyslog.conf
}

CONFFILES_${PN} = "${sysconfdir}/rsyslog.conf"
