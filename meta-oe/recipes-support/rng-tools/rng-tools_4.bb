SUMMARY = "Random number generator daemon"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS_append_libc-uclibc = " argp-standalone"

SRC_URI = "http://heanet.dl.sourceforge.net/sourceforge/gkernel/${BP}.tar.gz \
           file://init \
           file://default"

SRC_URI[md5sum] = "ae89dbfcf08bdfbea19066cfbf599127"
SRC_URI[sha256sum] = "b71bdfd4222c05e8316001556be90e1606f2a1bac3efde60153bd84e873cc195"

inherit autotools update-rc.d

do_install_append() {
    install -d "${D}${sysconfdir}/init.d"
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/rng-tools
    sed -i -e 's,/etc/,${sysconfdir}/,' -e 's,/usr/sbin/,${sbindir},' \
        ${D}${sysconfdir}/init.d/rng-tools

    install -d "${D}${sysconfdir}/default"
    install -m 0644 ${WORKDIR}/default ${D}${sysconfdir}/default
}

INITSCRIPT_NAME = "rng-tools"
INITSCRIPT_PARAMS = "defaults"
