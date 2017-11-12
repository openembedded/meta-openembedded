SUMMARY  = "pandas library for high-performance data analysis tools"
DESCRIPTION = "pandas is an open source, BSD-licensed library providing \
high-performance, easy-to-use data structures and data analysis tools for \
the Python programming language."
HOMEPAGE = "http://pandas.pydata.org/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ee0470f2de336c370a71c2f8d5e81c11"

SRC_URI[md5sum] = "6f14884caaa87a0824b5e93fe032b904"
SRC_URI[sha256sum] = "5cd5cb30e72eeaf202f0e5e180780b897570e889d2db328c689a5a263405c559"

PYPI_PACKAGE = "pandas"
inherit pypi

DEPENDS += " \
    ${PYTHON_PN}-numpy-native \
"

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-numpy \
    ${PYTHON_PN}-dateutil \
    ${PYTHON_PN}-pytz \
"
