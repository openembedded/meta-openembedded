SUMMARY = "A component library to support SBC display drivers"
DESCRIPTION = "A component library to support SBC display drivers"
HOMEPAGE = "https://github.com/rm-hull/luma.core"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=eda804060ba2312e41fe96b6fa334fd7"

inherit pypi setuptools3

SRC_URI[md5sum] = "a738326f46c860acf6a4a9b2ec36ba73"
SRC_URI[sha256sum] = "972dc312b2831fe4c6b51943cc36903a9508cab8d9f2631a7ee485695924d34b"

CLEANBROKEN = "1"

PYPI_PACKAGE = "luma.core"

RDEPENDS_${PN} += " \
	${PYTHON_PN}-pillow \
	${PYTHON_PN}-threading \
	${PYTHON_PN}-smbus2 \
"
