SUMMARY = "Filesystem events monitoring"
DEPENDS = "${PYTHON_PN}-argh"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[sha256sum] = "fd4b56cfbe0d0d9c4fc431aceca75700a7f12d3737dc2e9cb2ec2ba649680425"

inherit pypi setuptools3

RDEPENDS_${PN} = " \
    ${PYTHON_PN}-argh \
    ${PYTHON_PN}-pathtools3 \
    ${PYTHON_PN}-pyyaml \
    ${PYTHON_PN}-requests \
"

BBCLASSEXTEND = "native nativesdk"
