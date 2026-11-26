DESCRIPTION = "GEOS - Geometry Engine, Open Source"
HOMEPAGE = "http://trac.osgeo.org/geos/"
SECTION = "libs"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "http://download.osgeo.org/${BPN}/${BP}.tar.bz2"
SRC_URI[sha256sum] = "8eb6dc2ea78b00c2f5109c6b0dba9e028c60bad42d7b1b0cc9952b12dc71f571"

UPSTREAM_CHECK_URI = "https://download.osgeo.org/geos/"
UPSTREAM_CHECK_REGEX = "geos-(?P<pver>\d+(\.\d+)+)\.tar\.bz2"

inherit cmake pkgconfig binconfig

PACKAGES =+ "geoslib ${PN}-c1"

DESCRIPTION:${PN}lib = "Geometry engine for Geographic Information Systems - C++ Library"
FILES:${PN}lib += "${libdir}/libgeos.so.*"

DESCRIPTION:${PN}-c1 = "Geometry engine for Geographic Information Systems - C Library"
FILES:${PN}-c1 += "${libdir}/libgeos_c.so.*"

ALLOW_EMPTY:${PN} = "1"
RDEPENDS:${PN} += "geoslib ${PN}-c1"
