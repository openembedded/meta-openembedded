SUMMARY = "Simple DirectMedia Layer (SDL) sdl2-compat"
DESCRIPTION = "This code is a compatibility layer; it provides a binary and source compatible \
API for programs written against SDL2, but it uses SDL3 behind the scenes. If you are \
writing new code, please target SDL3 directly and do not use this layer."
HOMEPAGE = "http://www.libsdl.org"
BUGTRACKER = "http://bugzilla.libsdl.org/"

SECTION = "libs"

LICENSE = "Zlib"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=98241180d857fe975e4b60d44d6c01a5"

SRC_URI = "http://www.libsdl.org/release/sdl2-compat-${PV}.tar.gz"
SRC_URI[sha256sum] = "ae85222c007f7e2acb927c7a47c12726f56478c6f3f35ee0da1ac929f8beb53e"
S = "${UNPACKDIR}/sdl2-compat-${PV}"

DEPENDS += "libsdl3"

PROVIDES = "virtual/libsdl2"

inherit cmake pkgconfig upstream-version-is-even features_check

REQUIRED_DISTRO_FEATURES = "opengl x11"

do_install:append() {
	mv ${D}${libdir}/pkgconfig/sdl2-compat.pc ${D}${libdir}/pkgconfig/sdl2.pc
}

FILES:${PN} += "${datadir}/licenses"

RCONFLICTS:${PN} = "libsdl2"
RPROVIDES:${PN} = "libsdl2"

BBCLASSEXTEND = "nativesdk native"
