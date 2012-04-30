DESCRIPTION = "Media controller control application"
LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=d749e86a105281d7a44c2328acebc4b0"

SRC_URI = "git://git.ideasonboard.org/media-ctl.git;protocol=git"
SRCREV = "46bec667b675573cf1ce698c68112e3dbd31930e"

PV = "0.0.1"
PR = "r1"
S = "${WORKDIR}/git"

inherit autotools

# It needs some kernel definitions only for v4l2, so it isn't machine specific
EXTRA_OECONF = "--with-kernel-headers=${STAGING_KERNEL_DIR}"

PACKAGES =+ "libmediactl libv4l2subdev"
FILES_libmediactl = "${libdir}/libmediactl${SOLIBS}"
FILES_libv4l2subdev = "${libdir}/libv4l2subdev${SOLIBS}"

