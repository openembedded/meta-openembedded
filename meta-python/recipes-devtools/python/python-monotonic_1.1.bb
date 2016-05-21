SUMMARY = "An implementation of time.monotonic() for Python 2.0 through 3.2"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d2794c0df5b907fdace235a619d80314"

SRC_URI[md5sum] = "8271b79de174dc3ef00535caef38be85"
SRC_URI[sha256sum] = "255c31929e1a01acac4ca709f95bd6d319d6112db3ba170d1fe945a6befe6942"
PYPI_PACKAGE_HASH = "3f3b7ee821b1314fbf35e6f5d50fce1b853764661a7f59e2da1cb58d33c3fdd9"

inherit pypi setuptools

RDEPENDS_${PN} += "${PYTHON_PN}-ctypes ${PYTHON_PN}-re"
