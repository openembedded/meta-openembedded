SUMMARY = "MMS stream protocol library"
HOMEPAGE = "http://sourceforge.net/projects/libmms/"
SECTION = "libs/multimedia"

LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=fad9b3332be894bab9bc501572864b29"

DEPENDS = "glib-2.0"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/libmms/libmms/${PV}/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "9f63aa363deb4874e072a45850161bff"
SRC_URI[sha256sum] = "01931b62172d7d7050fc9ef9b1b64162f3b6e9f6cc4415170192a32a0b7ea432"

inherit autotools pkgconfig
