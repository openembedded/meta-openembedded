DESCRIPTION = "Xfce4 development tools"
HOMEPAGE = "http://www.xfce.org"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"
DEPENDS = "glib-2.0"

inherit autotools

BBCLASSEXTEND = "native"

SRC_URI = "http://archive.xfce.org/src/xfce/${BPN}/${@'${PV}'[0:4]}/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "372b74f432e7e02d946c2ea9d3de2805"
SRC_URI[sha256sum] = "f79649bbfbbf16881494ee9f67035063765aec12e23ab9bac31de2c8a4f32bf8"

do_install_append() {
    install -d ${D}${datadir}/aclocal
    install -m 644 m4macros/*.m4 ${D}${datadir}/aclocal/
}

FILES_${PN} += "${datadir}/xfce4/dev-tools/m4macros/*.m4"
