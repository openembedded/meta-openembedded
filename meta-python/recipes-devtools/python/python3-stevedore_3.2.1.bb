DESCRIPTION = "Manage dynamic plugins for Python applications"
HOMEPAGE = "https://docs.openstack.org/stevedore/latest/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[md5sum] = "5f5660cebaefebbde4607e44e050a3b2"
SRC_URI[sha256sum] = "a34086819e2c7a7f86d5635363632829dab8014e5fd7be2454c7cba84ac7514e"

DEPENDS += "${PYTHON_PN}-pbr-native"

inherit pypi setuptools3

RDEPENDS_${PN} += "${PYTHON_PN}-pbr ${PYTHON_PN}-six"

BBCLASSEXTEND = "native"
