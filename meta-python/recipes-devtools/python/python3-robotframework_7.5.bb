SUMMARY = "A Python generic test automation framework"
DESCRIPTION = "Generic open source test atomation framework for acceptance\
testing and acceptance test-driven development (ATDD). It has easy-to-use\
tabular test data syntax and it utilizes the keyword-driven testing approach.\
Its testing capabilities can be extended by test libraries implemented either\
with Python or Java, and users can create new higher-level keywords from\
existing ones using the same syntax that is used for creating test cases."
HOMEPAGE = "https://robotframework.org"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit pypi setuptools3

SRC_URI[sha256sum] = "ff6233ff752a200ece4d0a6c59f6f9f7d0e96dcff0a7a3458296b997b812482e"

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
