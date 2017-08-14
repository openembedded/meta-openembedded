SUMMARY = "Python Remote Objects"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=378acef375e17a3bff03bd0f78c53220"

SRC_URI[md5sum] = "34b25f257db2318072d1683c4a4f1ba2"
SRC_URI[sha256sum] = "c465cb2ea2a90b887988d4249de8c0566bdfb16101fdc570e07e598a92e94d1e"

PYPI_PACKAGE = "Pyro4"

inherit pypi setuptools

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-selectors34 \
    ${PYTHON_PN}-serpent \
    ${PYTHON_PN}-threading \
    ${PYTHON_PN}-zlib \
    "
