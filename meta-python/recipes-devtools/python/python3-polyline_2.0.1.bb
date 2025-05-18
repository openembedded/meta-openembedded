SUMMARY = "A Python implementation of Google's Encoded Polyline Algorithm Format"
HOMEPAGE = "https://github.com/frederickjansen/polyline"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fb8d1dc685695195bb3c1e48adfef48"

SRC_URI[sha256sum] = "74cb5cea098dddf09d1a5a1f17af9184d371cbf3e9723de0194e530ec39ca1f6"

inherit pypi python_setuptools_build_meta ptest

RDEPENDS:${PN} += "python3-six"

BBCLASSEXTEND = "native nativesdk"

SRC_URI += " \
	file://run-ptest \
"

RDEPENDS:${PN}-ptest += " \
	${PYTHON_PN}-pytest \
"

do_install_ptest() {
	install -d ${D}${PTEST_PATH}/test
	cp -rf ${S}/tests/* ${D}${PTEST_PATH}/test/
}
