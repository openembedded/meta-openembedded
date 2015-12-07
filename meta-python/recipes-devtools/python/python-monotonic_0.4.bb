SUMMARY = "An implementation of time.monotonic() for Python 2.0 through 3.2"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d2794c0df5b907fdace235a619d80314"

SRC_URI[md5sum] = "1919cc3aff2a1b907fe24c1f801343ef"
SRC_URI[sha256sum] = "852f656adbf623ee859def6ca2f5498f4cae3256f8320d5c50570ee8a0592ab6"

inherit pypi

RDEPENDS_${PN} += "${PYTHON_PN}-ctypes ${PYTHON_PN}-re"
