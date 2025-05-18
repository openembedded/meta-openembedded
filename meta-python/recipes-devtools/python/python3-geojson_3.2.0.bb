SUMMARY = "Python bindings and utilities for GeoJSON"
HOMEPAGE = "https://pypi.org/project/geojson/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=f77f2ed49768c8d4c79ba874c0f94d8a"

SRC_URI[sha256sum] = "b860baba1e8c6f71f8f5f6e3949a694daccf40820fa8f138b3f712bd85804903"

inherit pypi setuptools3 ptest

SRC_URI += "file://run-ptest"

RDEPENDS:${PN}-ptest += " \
    python3-pytest \
    python3-unittest-automake-output \
"

do_install_ptest() {
	install -d ${D}${PTEST_PATH}/tests
	cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

RDEPENDS:${PN} += "python3-simplejson python3-math"

BBCLASSEXTEND = "native nativesdk"
