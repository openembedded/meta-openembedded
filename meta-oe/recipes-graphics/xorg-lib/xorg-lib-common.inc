HOMEPAGE = "http://www.x.org"
BUGTRACKER = "https://bugs.freedesktop.org/enter_bug.cgi?product=xorg"
SECTION = "x11/libs"
LICENSE = "MIT-X"
DEPENDS = "util-macros"

XORG_PN = "${BPN}"
INC_PR = "r9"

SRC_URI = "${XORG_MIRROR}/individual/lib/${XORG_PN}-${PV}.tar.bz2"

S = "${WORKDIR}/${XORG_PN}-${PV}"

inherit autotools pkgconfig

EXTRA_OECONF = "--enable-malloc0returnsnull"

BBCLASSEXTEND = "native nativesdk"
