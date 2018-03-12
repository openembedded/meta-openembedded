SUMMARY = "JSON Web Token implementation in Python"
SUMMARY = "JSON Web Token implementation in Python"
DESCRIPTION = "A Python implementation of JSON Web Token draft 32.\
 Original implementation was written by https://github.com/progrium"
HOMEPAGE = "http://github.com/jpadilla/pyjwt"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=68626705a7b513ca8d5f44a3e200ed0c"

SRC_URI[md5sum] = "3c3762c88b210763a311a5db06aa7d6f"
SRC_URI[sha256sum] = "9c3016e4a292151c5396e25cc0c28c4e1cdf13fa19118eb84f500f9670e3f628"

PYPI_PACKAGE = "PyJWT"
inherit pypi

RDEPENDS_${PN} = "${PYTHON_PN}-cryptography"

BBCLASSEXTEND = "native nativesdk"
