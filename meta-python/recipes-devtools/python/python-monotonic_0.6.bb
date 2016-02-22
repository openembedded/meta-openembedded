SUMMARY = "An implementation of time.monotonic() for Python 2.0 through 3.2"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d2794c0df5b907fdace235a619d80314"

SRC_URI[md5sum] = "2f77ba5a56051b0442b1c74fd05dafb5"
SRC_URI[sha256sum] = "2bc780a16024427cb4bfbfff77ed328484cf6937a787cc50055b83b13b653e74"

inherit pypi setuptools

RDEPENDS_${PN} += "${PYTHON_PN}-ctypes ${PYTHON_PN}-re"
