SUMMARY = "deflate64 compression/decompression library"
HOMEPAGE = "https://codeberg.org/miurahr/inflate64"
LICENSE = "LGPL-2.1-or-later"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

inherit setuptools3 pypi

SRC_URI[sha256sum] = "b52dd8fefd2ba179e5dfa18d6eca7e2fc822584616271c039d5ef1f9ca90c71c"

PYPI_PACKAGE = "inflate64"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-core \
    ${PYTHON_PN}-importlib-metadata \
"
