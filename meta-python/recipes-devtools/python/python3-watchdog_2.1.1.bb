SUMMARY = "Filesystem events monitoring"
DEPENDS = "${PYTHON_PN}-argh"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[sha256sum] = "2894440b4ea95a6ef4c5d152deedbe270cae46092682710b7028a04d6a6980f6"

inherit pypi setuptools3

RDEPENDS_${PN} = " \
    ${PYTHON_PN}-argh \
    ${PYTHON_PN}-pathtools3 \
    ${PYTHON_PN}-pyyaml \
    ${PYTHON_PN}-requests \
"

BBCLASSEXTEND = "native nativesdk"
