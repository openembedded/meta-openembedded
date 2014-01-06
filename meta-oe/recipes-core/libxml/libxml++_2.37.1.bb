SUMMARY = "C++ wrapper for libxml library"
DESCRIPTION = "C++ wrapper for libxml library"
HOMEPAGE = "http://libxmlplusplus.sourceforge.net"
BUGTRACKER = "http://bugzilla.gnome.org/buglist.cgi?product=libxml%2B%2B"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34 "

SHRT_VER = "${@d.getVar('PV',True).split('.')[0]}.${@d.getVar('PV',True).split('.')[1]}"
SRC_URI = "${GNOME_MIRROR}/${BPN}/${SHRT_VER}/${BP}.tar.xz \
    file://libxml++_ptest.patch \
    file://run-ptest \
"
SRC_URI[md5sum] = "2f9372a6eba6e40206c11f558a8fbc32"
SRC_URI[sha256sum] = "f3b183600532a92af355719210223f858857092b8b1531c7907155c59a6db39f"

DEPENDS = "libxml2 glibmm"

inherit autotools pkgconfig ptest

do_compile_ptest() {
  oe_runmake -C examples buildtest
}

FILES_${PN}-doc += "${datadir}/devhelp"
FILES_${PN}-dev += "${libdir}/libxml++-2.6/include/libxml++config.h"

RDEPENDS_${PN}-ptest += "make"
