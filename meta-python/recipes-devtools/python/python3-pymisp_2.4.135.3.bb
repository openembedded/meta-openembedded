DESCRIPTION = "Python API for MISP"
HOMEPAGE = "https://github.com/MISP/PyMISP"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a3639cf5780f71b125d3e9d1dc127c20"

SRC_URI[sha256sum] = "f0bbdd77358223ba75c9cc40f192c7a2a7a5838bdd08b28381f71d220151ea8a"

inherit pypi setuptools3

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-dateutil \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-jsonschema \
    ${PYTHON_PN}-requests \
    ${PYTHON_PN}-six \
"

# Fixes: python3-pymisp requires /bin/bash, but no
# providers found in RDEPENDS_python3-pymisp? [file-rdep
RDEPENDS_${PN} += "bash"
