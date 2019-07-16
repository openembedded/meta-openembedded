SUMMARY = "Xfce4 development tools"
HOMEPAGE = "http://www.xfce.org"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"
DEPENDS = "glib-2.0"

inherit autotools pkgconfig

BBCLASSEXTEND = "native"

SRC_URI = "http://archive.xfce.org/src/xfce/${BPN}/${@'${PV}'[0:4]}/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "dc7ce082ed0fb9a8f2c0953a939b8d42"
SRC_URI[sha256sum] = "d24d4db2151a35324c672d4596a6a256f7d92727fe6a9d23648ab53f5207ba3b"

do_install_append() {
    install -d ${D}${datadir}/aclocal
    install -m 644 ${S}/m4macros/*.m4 ${D}${datadir}/aclocal/
}

FILES_${PN} += "${datadir}/xfce4/dev-tools/m4macros/*.m4"

RDEPENDS_${PN} = "bash"
