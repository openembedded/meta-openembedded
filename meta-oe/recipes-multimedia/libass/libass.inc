DESCRIPTION = "libass is a portable subtitle renderer for the ASS/SSA (Advanced Substation Alpha/Substation Alpha) subtitle format. It is mostly compatible with VSFilter."
HOMEPAGE = "http://code.google.com/p/libass/"
SECTION = "libs/multimedia"

LICENSE = "ISC"

DEPENDS = "enca fontconfig freetype libpng"

INC_PR = "r0"

SRC_URI = "http://${PN}.googlecode.com/files/${P}.tar.bz2"

inherit autotools pkgconfig

OE_EXTRACONF = " \
	--enable-enca \
	--enable-fontconfig \
	--enable-png \
"

PACKAGES =+ "${PN}-tests"

FILES_${PN}-tests = " \
	${libdir}/test/test \
"
