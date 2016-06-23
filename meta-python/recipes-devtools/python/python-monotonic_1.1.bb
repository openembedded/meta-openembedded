SUMMARY = "An implementation of time.monotonic() for Python 2.0 through 3.2"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d2794c0df5b907fdace235a619d80314"

SRC_URI[md5sum] = "8271b79de174dc3ef00535caef38be85"
SRC_URI[sha256sum] = "255c31929e1a01acac4ca709f95bd6d319d6112db3ba170d1fe945a6befe6942"

inherit pypi setuptools

RDEPENDS_${PN} += "${PYTHON_PN}-ctypes ${PYTHON_PN}-re"
