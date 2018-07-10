DESCRIPTION = "Manage dynamic plugins for Python applications"
HOMEPAGE = "https://github.com/dreamhost/stevedore"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[md5sum] = "b736a71431a2ff5831bbff4a6ccec0c1"
SRC_URI[sha256sum] = "f1c7518e7b160336040fee272174f1f7b29a46febb3632502a8f2055f973d60b"

inherit pypi

RDEPENDS_${PN} += "${PYTHON_PN}-pbr ${PYTHON_PN}-six"

BBCLASSEXTEND = "native"
