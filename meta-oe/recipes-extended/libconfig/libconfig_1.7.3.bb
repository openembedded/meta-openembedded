SUMMARY = "C/C++ Configuration File Library"
DESCRIPTION = "Library for manipulating structured configuration files"
HOMEPAGE = "https://hyperrealm.github.io/libconfig/"
BUGTRACKER = "https://github.com/hyperrealm/libconfig/issues"
SECTION = "libs"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=fad9b3332be894bab9bc501572864b29"

SRC_URI = "git://github.com/hyperrealm/libconfig.git;protocol=https;branch=master"
SRCREV = "525922a6b49ca19235cdf307376e7c9c539d1b93"
S = "${WORKDIR}/git"

# needed when we modify grammar.y with above patch
DEPENDS += "bison-native"

UPSTREAM_CHECK_URI = "https://github.com/hyperrealm/libconfig/releases"
UPSTREAM_CHECK_REGEX = "Version (?P<pver>\d+(\.\d+)+)"

inherit autotools-brokensep pkgconfig

PACKAGE_BEFORE_PN = "${PN}++"
FILES:${PN}++ = "${libdir}/${BPN}++*${SOLIBS}"
