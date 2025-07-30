SUMMARY = "C++ wrapper for libxml library"
DESCRIPTION = "C++ wrapper for libxml library"
HOMEPAGE = "http://libxmlplusplus.sourceforge.net"
BUGTRACKER = "http://bugzilla.gnome.org/buglist.cgi?product=libxml%2B%2B"
SECTION = "libs"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34 "

DEPENDS = "libxml2 glibmm"

GNOMEBN = "libxml++"
inherit gnomebase ptest

S = "${UNPACKDIR}/libxml++-${PV}"

SRC_URI[archive.sha256sum] = "e9a23c436686a94698d2138e6bcbaf849121d63bfa0f50dc34fefbfd79566848"

UPSTREAM_CHECK_REGEX = "(?P<pver>\d+(\.\d+)+)"

FILES:${PN}-doc += "${datadir}/devhelp"
FILES:${PN}-dev += "${libdir}/libxml++-${@gnome_verdir("${PV}")}/include/libxml++config.h"
