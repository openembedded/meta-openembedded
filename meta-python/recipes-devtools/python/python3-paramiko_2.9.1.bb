DESCRIPTION = "The leading native Python SSHv2 protocol library."
HOMEPAGE = "https://github.com/paramiko/paramiko/"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fd0120fc2e9f841c73ac707a30389af5"

SRC_URI[sha256sum] = "a1fdded3b55f61d23389e4fe52d9ae428960ac958d2edf50373faa5d8926edd0"

PYPI_PACKAGE = "paramiko"

inherit pypi setuptools3

CLEANBROKEN = "1"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-bcrypt \
    ${PYTHON_PN}-cryptography \
    ${PYTHON_PN}-pynacl \
    ${PYTHON_PN}-unixadmin \
"
