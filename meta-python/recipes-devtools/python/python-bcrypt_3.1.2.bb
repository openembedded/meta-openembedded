DESCRIPTION = "Modern password hashing for your software and your servers."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8f7bb094c7232b058c7e9f2e431f389c"

DEPENDS = "python-cffi-native"

SRC_URI[md5sum] = "c5a79004fc4ad589615e96a6fe45b664"
SRC_URI[sha256sum] = "346e175c820a111c17d4c2def181a96e1826652edb0bb16e565085ed542785aa"

inherit pypi setuptools

RDEPENDS_${PN}_class-target = "\
    ${PYTHON_PN}-cffi \
    ${PYTHON_PN}-six \
"
