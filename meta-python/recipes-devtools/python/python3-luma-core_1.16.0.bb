SUMMARY = "A component library to support SBC display drivers"
DESCRIPTION = "A component library to support SBC display drivers"
HOMEPAGE = "https://github.com/rm-hull/luma.core"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=eda804060ba2312e41fe96b6fa334fd7"

inherit pypi setuptools3

SRC_URI[md5sum] = "52ab2cdce4222aa71d9ac254a806dccc"
SRC_URI[sha256sum] = "ca523f606f8fff2c9843d48b415a0a748bb2c96270eccac97d9caa9ae6810c94"

CLEANBROKEN = "1"

PYPI_PACKAGE = "luma.core"

RDEPENDS_${PN} += " \
	${PYTHON_PN}-pillow \
	${PYTHON_PN}-threading \
	${PYTHON_PN}-smbus2 \
"
