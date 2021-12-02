SUMMARY = "RDFLib is a pure Python package for working with RDF"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b6cde159c801514e0e45a40cf0a9d3d9"

SRC_URI[sha256sum] = "6136ae056001474ee2aff5fc5b956e62a11c3a9c66bb0f3d9c0aaa5fbb56854e"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-isodate \
    ${PYTHON_PN}-pyparsing \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-numbers \
    ${PYTHON_PN}-xml \
"

BBCLASSEXTEND = "native nativesdk"
