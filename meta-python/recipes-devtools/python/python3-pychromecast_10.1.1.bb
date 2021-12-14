SUMMARY = "Library for Python 3.6+ to communicate with the Google Chromecast."
HOMEPAGE = "https://github.com/balloob/pychromecast"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b1dbd4e85f47b389bdadee9c694669f5"

SRC_URI[sha256sum] = "33a47d551ae8b6f93129564828e72e7f4dcb26c9f8812328870cf300ee45438f"

PYPI_PACKAGE = "PyChromecast"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-zeroconf \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-requests \
    ${PYTHON_PN}-protobuf \
"
