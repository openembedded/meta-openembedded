SUMMARY  = "Appling JSON patches in Python 2.6+ and 3.x"
HOMEPAGE = "https://github.com/stefankoegl/python-json-patch"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=32b15c843b7a329130f4e266a281ebb3"

inherit pypi setuptools

SRC_URI[md5sum] = "b82b9ecad4cdef86bf0b88cbdbf7a8b9"
SRC_URI[sha256sum] = "b12594a0cfe634bdd2a5c027250af2dd84932d493568e88ae722871edc3eb02d"

RDEPENDS_${PN} += "${PYTHON_PN}-json ${PYTHON_PN}-jsonpointer ${PYTHON_PN}-netclient ${PYTHON_PN}-re ${PYTHON_PN}-stringold"

