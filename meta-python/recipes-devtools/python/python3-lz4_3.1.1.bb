DESCRIPTION = "python bindings for the lz4 compression library by Yann Collet"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6231efa4dd4811e62407314d90a57573"

DEPENDS += " \
    ${PYTHON_PN}-setuptools-scm-native \
    ${PYTHON_PN}-pkgconfig-native \
"

SRC_URI[sha256sum] = "1ac354804cb2d5fb3d213857a6bf8590a301ef051cc16fbb4938bd2d6e524bda"

inherit pypi setuptools3

BBCLASSEXTEND = "native nativesdk"
