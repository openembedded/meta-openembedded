DESCRIPTION = "Python API for MISP"
HOMEPAGE = "https://github.com/MISP/PyMISP"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=97a1b2b62a1315b13e1d7f1f633624ee"

SRC_URI[md5sum] = "c82084718360bb3ad92eb48a07ec8914"
SRC_URI[sha256sum] = "ad36f7c4507dc06afaff4166d83c86da12efb839854752720b6fdbc9bb3e20e8"

RDEPENDS_${PN} += " ${PYTHON_PN}-requests ${PYTHON_PN}-json ${PYTHON_PN}-jsonschema"
