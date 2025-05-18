DESCRIPTION = "Python API for MISP"
HOMEPAGE = "https://github.com/MISP/PyMISP"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a3639cf5780f71b125d3e9d1dc127c20"

SRC_URI = "git://github.com/MISP/PyMISP.git;protocol=https;branch=main"
SRCREV = "b1892efb6a078d1370cee51c9103f3a591c628d2"
S = "${WORKDIR}/git"

inherit python_poetry_core

PIP_INSTALL_PACKAGE = "pymisp"

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-dateutil \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-jsonschema \
    ${PYTHON_PN}-requests \
    ${PYTHON_PN}-six \
    ${PYTHON_PN}-deprecated \
    ${PYTHON_PN}-wrapt \
"

# Fixes: python3-pymisp requires /bin/bash, but no
# providers found in RDEPENDS:python3-pymisp? [file-rdep]
RDEPENDS:${PN} += "bash"
