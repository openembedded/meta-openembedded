DESCRIPTION = "Programs for accessing Microsoft Word documents"
HOMEPAGE = "http://wvware.sourceforge.net/"
LICENSE = "GPLv2"
DEPENDS = "libgsf glib-2.0"
PR = "r3"

SRC_URI = "${SOURCEFORGE_MIRROR}/wvware/wv-${PV}.tar.gz \
           file://pkgconfig.patch;patch=1"

inherit autotools pkgconfig

S = "${WORKDIR}/${PN}-${PV}"

EXTRA_OECONF = ""
