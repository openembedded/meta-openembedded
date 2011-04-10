HOMEPAGE = "http://www.x.org"
SECTION = "x11/utils"
LICENSE = "MIT-X"

INC_PR = "r2"

SRC_URI = "${XORG_MIRROR}/individual/util/${BPN}-${PV}.tar.gz"

S = "${WORKDIR}/${BPN}-${PV}"

inherit autotools pkgconfig
