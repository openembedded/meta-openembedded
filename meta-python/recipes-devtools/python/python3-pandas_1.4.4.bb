SUMMARY  = "pandas library for high-performance data analysis tools"
DESCRIPTION = "pandas is an open source, BSD-licensed library providing \
high-performance, easy-to-use data structures and data analysis tools for \
the Python programming language."
HOMEPAGE = "http://pandas.pydata.org/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3f23c5c092b74d245d48eeef72bc3fd2"

SRC_URI[sha256sum] = "ab6c0d738617b675183e5f28db32b5148b694ad9bba0a40c3ea26d96b431db67"

inherit pypi setuptools3

DEPENDS += " \
    ${PYTHON_PN}-numpy-native ${PYTHON_PN}-cython-native \
"

CFLAGS:append:toolchain-clang = " -Wno-error=deprecated-declarations"

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-numpy \
    ${PYTHON_PN}-dateutil \
    ${PYTHON_PN}-dateutil-zoneinfo \
    ${PYTHON_PN}-pytz \
    ${PYTHON_PN}-profile \
"
