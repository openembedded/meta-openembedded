SUMMARY = "PROJ.4 - Cartographic Projections library"
HOMEPAGE = "http://trac.osgeo.org/proj/"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27445198ba1500f508fce2b183ce0ff"
DEPENDS = "sqlite3 sqlite3-native"

SRC_URI = "http://download.osgeo.org/${BPN}/${BP}.tar.gz"
SRC_URI[sha256sum] = "003cd4010e52bb5eb8f7de1c143753aa830c8902b6ed01209f294846e40e6d39"

inherit cmake lib_package

EXTRA_OECMAKE = "-DBUILD_TESTING=OFF -DSQLITE3_LIBRARY:STRING=sqlite3"

FILES:${PN} += "${datadir}/proj"

BBCLASSEXTEND = "native"

PACKAGECONFIG ?= "curl shared"
PACKAGECONFIG:append:class-native = " apps"

PACKAGECONFIG[apps] = "-DBUILD_APPS=ON, -DBUILD_APPS=OFF"
PACKAGECONFIG[curl] = "-DENABLE_CURL=ON,-DENABLE_CURL=OFF,curl"
PACKAGECONFIG[shared] = "-DBUILD_SHARED_LIBS=ON,-DBUILD_SHARED_LIBS=OFF,,"
PACKAGECONFIG[tiff] = "-DENABLE_TIFF=ON,-DENABLE_TIFF=OFF,tiff"
