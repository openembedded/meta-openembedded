DESCRIPTION = "XCB: an X protocol C binding"
SECTION = "x11/libs"
LICENSE = "MIT-X"
HOMEPAGE = "http://xcb.freedesktop.org"
LIC_FILES_CHKSUM = "file://COPYING;md5=d763b081cb10c223435b01e00dc0aba7"
DEPENDS = "python-native"

SRC_URI = "http://xcb.freedesktop.org/dist/xcb-proto-${PV}.tar.bz2"

BBCLASSEXTEND = "native nativesdk"

inherit autotools pkgconfig

INC_PR = "r0"

FILES_${PN}-dev += "${datadir}/xcb/*.xml"
ALLOW_EMPTY_${PN} = "1"

