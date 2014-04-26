SUMMARY = "Little cms is a small-footprint, speed optimized color management engine"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=6c786c3b7a4afbd3c990f1b81261d516"
SRC_URI = "${SOURCEFORGE_MIRROR}/lcms/lcms2-${PV}.tar.gz"
SRC_URI[md5sum] = "f4c08d38ceade4a664ebff7228910a33"
SRC_URI[sha256sum] = "5172528839647c54c3da211837225e221be93e4733f5b5e9f57668f7107e14b1"

DEPENDS = "tiff"

BBCLASSEXTEND = "native"

S = "${WORKDIR}/lcms2-${PV}"

inherit autotools
