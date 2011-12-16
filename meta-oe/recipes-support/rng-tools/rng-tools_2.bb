DESCRIPTION = "Random number generator daemon"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS_append_libc-uclibc = " argp-standalone"

SRC_URI = "http://heanet.dl.sourceforge.net/sourceforge/gkernel/${P}.tar.gz \
           file://init \
           file://default"

SRC_URI[md5sum] = "63d503191eabed630324c104cc024475"
SRC_URI[sha256sum] = "1126f0ecc8cab3af14a562cddc5d8ffeef47df7eba34a7aadcdee35a25ec2b1e"

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
