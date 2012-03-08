DESCRIPTION = "Little cms is a small-footprint, speed optimized color management engine."
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=156745cad721a8783cb847e82b79f586"
SRC_URI = "${SOURCEFORGE_MIRROR}/lcms/lcms-${PV}.tar.gz"
DEPENDS = "tiff"

PR = "r4"

BBCLASSEXTEND = "native"

inherit autotools

SRC_URI[md5sum] = "07bdbb4cfb05d21caa58fe3d1c84ddc1"
SRC_URI[sha256sum] = "5ef3b4dab30956772009e29dba33fe1256cf9da161106a1e70b0966c96d14583"
