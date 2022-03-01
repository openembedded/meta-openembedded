DESCRIPTION = "python bindings for the lz4 compression library by Yann Collet"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6231efa4dd4811e62407314d90a57573"

DEPENDS += " \
    ${PYTHON_PN}-setuptools-scm-native \
    ${PYTHON_PN}-pkgconfig-native \
"

SRC_URI[sha256sum] = "57c5dfd3b7dae833b0d2b2c1aafd7f9d0dfcab40683d183d010c67c9fd1beca3"

inherit pypi setuptools3

BBCLASSEXTEND = "native nativesdk"
