DESCRIPTION = "Graphviz protocol implementation"
HOMEPAGE = "https://graphviz.readthedocs.io/en/stable/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=87cd8818b7e63c6a9c580034e80d7521"

SRC_URI[sha256sum] = "0f04e5f939d3a839b524283d590e941892c56e75e60e0f5238c431264f490022"

inherit pypi setuptools3

PYPI_PACKAGE_EXT = "zip"

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-logging \
"

BBCLASSEXTEND = "native nativesdk"
