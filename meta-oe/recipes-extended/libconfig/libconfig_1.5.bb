SUMMARY = "C/C++ Configuration File Library"
DESCRIPTION = "Library for manipulating structured configuration files"
HOMEPAGE = "https://hyperrealm.github.io/libconfig"
SECTION = "libs"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=fad9b3332be894bab9bc501572864b29"

SRC_URI = "git://github.com/hyperrealm/libconfig.git;protocol=git"
SRCREV = "f9f23d7a95608936ea7d839731dbd56f1667b7ed"

S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig
