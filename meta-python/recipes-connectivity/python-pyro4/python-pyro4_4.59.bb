SUMMARY = "Python Remote Objects"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=378acef375e17a3bff03bd0f78c53220"

SRC_URI[md5sum] = "a93a41038dba964011772e0d3e4651a0"
SRC_URI[sha256sum] = "6a39dadbd2a83b6fd5ab7f5402f8a4befd467b5c0404b8610a8797f748b72a38"

PYPI_PACKAGE = "Pyro4"

inherit pypi setuptools

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-selectors34 \
    ${PYTHON_PN}-serpent \
    ${PYTHON_PN}-threading \
    ${PYTHON_PN}-zlib \
    "
