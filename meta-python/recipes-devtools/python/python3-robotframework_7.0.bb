SUMMARY = "A Python generic test automation framework"
DESCRIPTION = "Generic open source test atomation framework for acceptance\
testing and acceptance test-driven development (ATDD). It has easy-to-use\
tabular test data syntax and it utilizes the keyword-driven testing approach.\
Its testing capabilities can be extended by test libraries implemented either\
with Python or Java, and users can create new higher-level keywords from\
existing ones using the same syntax that is used for creating test cases."
HOMEPAGE = "http://robotframework.org"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit pypi setuptools3

PYPI_PACKAGE_EXT = "zip"

SRC_URI[sha256sum] = "04623f758346c917db182e17591ffa474090560c02ed5a64343902e72b7b4bd5"

RDEPENDS:${PN} += " \
    python3-shell \
    python3-pprint \
    python3-xml \
    python3-difflib \
    python3-threading \
    python3-html \
    python3-docutils \
    python3-ctypes \
    python3-logging \
    python3-numbers \
    python3-profile \
"
