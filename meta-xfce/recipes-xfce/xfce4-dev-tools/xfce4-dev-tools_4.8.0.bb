DESCRIPTION = "Xfce4 development tools"
HOMEPAGE = "http://www.xfce.org"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"

PR = "r2"

BBCLASSEXTEND = "native"

inherit autotools

do_install_append() {
       install -d ${D}${datadir}/aclocal
       install -m 644 m4macros/*.m4 ${D}${datadir}/aclocal/
}

FILES_${PN} += "${datadir}/xfce4/dev-tools/m4macros/*.m4"

SRC_URI = "http://archive.xfce.org/src/xfce/${BPN}/${@'${PV}'[0:3]}/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "9591299c49d92d00ba47974c42a735fa"
SRC_URI[sha256sum] = "7a1457e9dc839cb121a48e5c53c483ce02a3a5dbaf09376794a13bafe4194815"
