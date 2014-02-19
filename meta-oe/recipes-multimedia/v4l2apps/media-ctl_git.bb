SUMMARY = "Media controller control application"
LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=d749e86a105281d7a44c2328acebc4b0"

DEPENDS = "linux-libc-headers"

SRC_URI = "git://git.ideasonboard.org/media-ctl.git"
SRCREV = "a6ec4a37028952ffd6e62eb52648cf66248eb519"

PV = "0.0.1"
PR = "r4"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF = "--with-kernel-headers=${STAGING_EXECPREFIXDIR}"

PACKAGES =+ "libmediactl libv4l2subdev"
FILES_libmediactl = "${libdir}/libmediactl${SOLIBS}"
FILES_libv4l2subdev = "${libdir}/libv4l2subdev${SOLIBS}"

