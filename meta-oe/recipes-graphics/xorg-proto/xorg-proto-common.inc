DESCRIPTION = "X protocol headers: ${BPN}"
HOMEPAGE = "http://www.x.org"
BUGTRACKER = "https://bugs.freedesktop.org/enter_bug.cgi?product=xorg"
SECTION = "x11/libs"
LICENSE = "MIT-X"

DEPENDS = "util-macros"

ALLOW_EMPTY = "1"

INC_PR = "r2"

SRC_URI = "${XORG_MIRROR}/individual/proto/${BPN}-${PV}.tar.bz2"

S = "${WORKDIR}/${BPN}-${PV}"

inherit autotools pkgconfig

BBCLASSEXTEND = "native nativesdk"
