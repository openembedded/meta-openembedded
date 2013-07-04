DESCRIPTION = "Library extending the SQLite core to support fully fledged Spatial SQL capabilities"
HOMEPAGE = "https://www.gaia-gis.it/fossil/libspatialite/"
SECTION = "libs"
DEPENDS = "proj geos sqlite3"

INC_PR = "r0"

LICENSE = "MPLv1.1 GPLv2+ LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=0e92e1a36cc384b60f5b31dde0bdd39e"

SRC_URI = "http://www.gaia-gis.it/gaia-sins/libspatialite-sources/libspatialite-${PV}.tar.gz"

inherit autotools pkgconfig

EXTRA_OECONF = "--enable-freexl=no"

