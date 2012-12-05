SUMMARY = "C++ wrapper for libxml library"
DESCRIPTION = "C++ wrapper for libxml library"
HOMEPAGE = "http://libxmlplusplus.sourceforge.net"
BUGTRACKER = "http://bugzilla.gnome.org/buglist.cgi?product=libxml%2B%2B"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34 "

PR = "r1"

SHRT_VER = "${@d.getVar('PV',True).split('.')[0]}.${@d.getVar('PV',True).split('.')[1]}"
SRC_URI = "${GNOME_MIRROR}/${BPN}/${SHRT_VER}/${BP}.tar.xz"

SRC_URI[md5sum] = "196a2dcdc84ab987fe3852b3f29cafd7"
SRC_URI[sha256sum] = "715a4214bbff90365cc8406a28e427febd90461006d608fbdcdcea7fc9891eaf"

DEPENDS = "libxml2 glibmm"

inherit autotools pkgconfig

FILES_${PN}-doc += "${datadir}/devhelp"
FILES_${PN}-dev += "${libdir}/libxml++-2.6/include/libxml++config.h"
