DESCRIPTION = "Rsyslog is an enhanced multi-threaded syslogd"
DEPENDS = "zlib libestr libjson"
DEPENDS += "${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}"
HOMEPAGE = "http://www.rsyslog.com/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=51d9635e646fb75e1b74c074f788e973"

SRC_URI = "http://www.rsyslog.com/files/download/rsyslog/${PN}-${PV}.tar.gz \
           file://initscript \
"

SRC_URI[md5sum] = "ebcc010a6205c28eb505c0fe862f32c6"
SRC_URI[sha256sum] = "276d094d1e4c62c770ec8a72723667f119eee038912b79cf3337d439bc2f9087"
           
inherit autotools pkgconfig systemd update-rc.d

EXTRA_OECONF += "${@base_contains('DISTRO_FEATURES', 'systemd', '--with-systemdsystemunitdir=${systemd_unitdir}/system/', '--without-systemdsystemunitdir', d)} --enable-cached-man-pages"

do_install_append() {
    install -d "${D}${sysconfdir}/init.d"
    install -m 755 ${WORKDIR}/initscript ${D}${sysconfdir}/init.d/rsyslogd
    install -m 755 ${S}/platform/redhat/rsyslog.conf ${D}${sysconfdir}/rsyslog.conf
}

FILES_${PN} += "${bindir}"

INITSCRIPT_NAME = "rsyslogd"
INITSCRIPT_PARAMS = "defaults"

CONFFILES_${PN} = "${sysconfdir}/rsyslog.conf"

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "${PN}.service"
