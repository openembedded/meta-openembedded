DESCRIPTION = "X protocol headers: ${BPN}"
HOMEPAGE = "http://www.x.org"
SECTION = "x11/libs"
LICENSE = "MIT-X"

DEPENDS = "util-macros"

ALLOW_EMPTY = "1"

INC_PR = "r2"

SRC_URI = "${XORG_MIRROR}/individual/proto/${BPN}-${PV}.tar.bz2;name=archive"

S = "${WORKDIR}/${BPN}-${PV}"

inherit autotools pkgconfig

