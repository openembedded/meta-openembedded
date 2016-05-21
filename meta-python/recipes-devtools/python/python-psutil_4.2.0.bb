SUMMARY = "A cross-platform process and system utilities module for Python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0f02e99f7f3c9a7fe8ecfc5d44c2be62"

SRC_URI[md5sum] = "713f259f917a0c26acfbb7e6ae632ef7"
SRC_URI[sha256sum] = "544f013a0aea7199e07e3efe5627f5d4165179a04c66050b234cc3be2eca1ace"
PYPI_PACKAGE_HASH = "a6bf5ce23dc9f50de662af3b4bf54812438c298634224924c4e18b7c3b57a2aa"

RDEPENDS_${PN} += " \
    python-subprocess \
    "

inherit pypi setuptools
