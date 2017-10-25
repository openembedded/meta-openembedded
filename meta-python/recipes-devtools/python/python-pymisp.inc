DESCRIPTION = "Python API for MISP"
HOMEPAGE = "https://github.com/MISP/PyMISP"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a3639cf5780f71b125d3e9d1dc127c20"

SRC_URI[md5sum] = "77c6276039a7e48d8ae1d335564f20c9"
SRC_URI[sha256sum] = "2ce6d8a9c59fe5f63b406b667fd40df8ebba0dee63844dfec95fb9d2892ae6af"

RDEPENDS_${PN} += " ${PYTHON_PN}-requests ${PYTHON_PN}-json ${PYTHON_PN}-jsonschema"
