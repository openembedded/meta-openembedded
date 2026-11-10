SUMMARY = "CSS selector library for python-beautifulsoup4"
HOMEPAGE = "https://github.com/facelessuser/soupsieve"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=74851a2f1e5c07496dcb452af6a6bf54"

SRC_URI[sha256sum] = "7621e3c5f6b371ed2c5eabaac5de300ac3d66d05e6d7f34783e31b63c4f90c75"

inherit pypi python_hatchling ptest-python-pytest

RDEPENDS:${PN} += " \
    python3-core \
    python3-datetime \
"
RDEPENDS:${PN}:append:class-target = " \
    python3-beautifulsoup4 \
"

RDEPENDS:${PN}-ptest += " \
    python3-beautifulsoup4 \
    python3-typing-extensions \
"

BBCLASSEXTEND = "native nativesdk"
