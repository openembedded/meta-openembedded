DESCRIPTION = "Programs for accessing Microsoft Word documents"
HOMEPAGE = "http://wvware.sourceforge.net/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6cbca01f1c9178aca280e8ff64d85b2f"

DEPENDS = "libgsf glib-2.0"
PR = "r4"

SRC_URI = "${SOURCEFORGE_MIRROR}/wvware/wv-${PV}.tar.gz \
           file://pkgconfig.patch"
SRC_URI[md5sum] = "b6319d5e75611fe2210453b5feb82c0c"
SRC_URI[sha256sum] = "a76f44468e78591e6d510d326702e7c3999d2b9dd3ab8ab8c1c9811fd5b111e4"

inherit autotools pkgconfig

S = "${WORKDIR}/${PN}-${PV}"

EXTRA_OECONF = ""
