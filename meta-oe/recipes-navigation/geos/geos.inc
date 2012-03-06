DESCRIPTION = "GEOS - Geometry Engine, Open Source"
HOMEPAGE = "http://trac.osgeo.org/geos/"
SECTION = "libs"

INC_PR = "r0"

LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"

SRC_URI = "http://download.osgeo.org/geos/geos-${PV}.tar.bz2"

inherit autotools pkgconfig

# libgeos-${PV}.so is needed in PV
FILES_SOLIBSDEV = ""
FIELS_${PN}-dev = "${libdir}/libgeos.so \
                   ${libdir}/libgeos_c.so"
FILES_${PN} += " ${libdir}/libgeos-${PV}.so"
INSANE_SKIP_${PN} = "dev-so"
