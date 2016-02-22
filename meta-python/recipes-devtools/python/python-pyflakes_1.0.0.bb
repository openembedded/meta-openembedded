SUMMARY = "passive checker of Python programs"
HOMEPAGE = "https://github.com/dreamhost/cliff"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://README.rst;md5=0f2ba771f63ad51756f4fb10e827220e"

SRC_URI[md5sum] = "914621d4c9546248419b435dd358eb6a"
SRC_URI[sha256sum] = "f39e33a4c03beead8774f005bd3ecf0c3f2f264fa0201de965fce0aff1d34263"

inherit pypi setuptools

RDEPENDS_${PN} += " \
    python-prettytable \
    python-cmd2 \
    python-pyparsing"
