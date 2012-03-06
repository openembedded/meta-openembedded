DESCRIPTION = "PROJ.4 - Cartographic Projections Library"
HOMEPAGE = "http://trac.osgeo.org/proj/"
SECTION = "libs"

INC_PR = "r0"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=74d9aaec5fa0cd734341e8c4dc91b608"

SRC_URI = "http://download.osgeo.org/proj/proj-${PV}.tar.gz"

inherit autotools pkgconfig

