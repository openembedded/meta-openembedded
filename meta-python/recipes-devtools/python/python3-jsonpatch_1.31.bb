SUMMARY  = "Appling JSON patches in Python 2.6+ and 3.x"
HOMEPAGE = "https://github.com/stefankoegl/python-json-patch"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=32b15c843b7a329130f4e266a281ebb3"

inherit pypi setuptools3

SRC_URI[sha256sum] = "ae6f3686fc71b53a278a9fdeb91afdc0e523e2f8f8a82cb2f04af90413a8eacb"

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-jsonpointer \
    ${PYTHON_PN}-netclient \
    ${PYTHON_PN}-stringold \
"
