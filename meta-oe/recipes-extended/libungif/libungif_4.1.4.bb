DESCRIPTION = "shared library for GIF images"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=ae11c61b04b2917be39b11f78d71519a"

SRC_URI = "${SOURCEFORGE_MIRROR}/giflib/libungif-4.x/libungif-${PV}/libungif-${PV}.tar.bz2"
SRC_URI[md5sum] = "76865bc1bed90ecb5992a1edcc4d6c15"
SRC_URI[sha256sum] = "708a7eac218d3fd8e8dfb13f1089d4e1e98246985180a17d6ecfca5a6bd4d332"

inherit autotools lib_package

PACKAGES =+ "${PN}-utils"

FILES_${PN}-utils = "${bindir}/*"

BBCLASSEXTEND = "native"
