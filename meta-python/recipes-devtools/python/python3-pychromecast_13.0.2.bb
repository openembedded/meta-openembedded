SUMMARY = "Library for Python 3.6+ to communicate with the Google Chromecast."
HOMEPAGE = "https://github.com/balloob/pychromecast"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b1dbd4e85f47b389bdadee9c694669f5"

SRC_URI[sha256sum] = "1b520e477492cd8fe022e4d041e676056d5fffbb6c05b2f4509801a06bc67bec"

PYPI_PACKAGE = "PyChromecast"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-zeroconf \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-requests \
    ${PYTHON_PN}-protobuf \
    ${PYTHON_PN}-compression \
"
