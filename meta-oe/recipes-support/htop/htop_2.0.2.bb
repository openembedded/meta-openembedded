SUMMARY = "htop process monitor"
HOMEPAGE = "http://htop.sf.net"
SECTION = "console/utils"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=c312653532e8e669f30e5ec8bdc23be3"

DEPENDS = "ncurses"
RDEPENDS_${PN} = "ncurses-terminfo"

SRC_URI = " \
    http://hisham.hm/htop/releases/${PV}/htop-${PV}.tar.gz \
    file://0001-Use-pkg-config.patch \
    "

SRC_URI[md5sum] = "7d354d904bad591a931ad57e99fea84a"
SRC_URI[sha256sum] = "179be9dccb80cee0c5e1a1f58c8f72ce7b2328ede30fb71dcdf336539be2f487"

LDFLAGS_append_libc-uclibc = " -lubacktrace"

do_configure_prepend () {
    rm -rf ${S}/config.h
}

inherit autotools
