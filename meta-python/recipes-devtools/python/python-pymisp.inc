DESCRIPTION = "Python API for MISP"
HOMEPAGE = "https://github.com/MISP/PyMISP"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a3639cf5780f71b125d3e9d1dc127c20"

SRC_URI[md5sum] = "0fe33de8e87120a46f917cb6bfa7baee"
SRC_URI[sha256sum] = "6f228e4050252002bd9093378e687cf5eb963fd8fdf3e561453376eda7bc396a"

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-jsonschema \
    ${PYTHON_PN}-requests \
    ${PYTHON_PN}-six \
"
