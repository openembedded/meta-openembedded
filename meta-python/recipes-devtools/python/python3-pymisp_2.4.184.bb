DESCRIPTION = "Python API for MISP"
HOMEPAGE = "https://github.com/MISP/PyMISP"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a3639cf5780f71b125d3e9d1dc127c20"

SRC_URI[sha256sum] = "fbd9089c867a63144562932862474910696ab57f4786a4b7c1a0b28ba204c0ae"

inherit python_poetry_core pypi

PIP_INSTALL_PACKAGE = "pymisp"

RDEPENDS:${PN} += " \
    python3-dateutil \
    python3-json \
    python3-jsonschema \
    python3-requests \
    python3-six \
    python3-deprecated \
    python3-wrapt \
"

# Fixes: python3-pymisp requires /bin/bash, but no
# providers found in RDEPENDS:python3-pymisp? [file-rdep]
RDEPENDS:${PN} += "bash"
