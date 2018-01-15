SUMMARY = "JSON Web Token implementation in Python"
SUMMARY = "JSON Web Token implementation in Python"
DESCRIPTION = "A Python implementation of JSON Web Token draft 32.\
 Original implementation was written by https://github.com/progrium"
HOMEPAGE = "http://github.com/jpadilla/pyjwt"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=68626705a7b513ca8d5f44a3e200ed0c"

SRC_URI[md5sum] = "739494379f582ca98c89fb7ac229915b"
SRC_URI[sha256sum] = "500be75b17a63f70072416843dc80c8821109030be824f4d14758f114978bae7"

PYPI_PACKAGE = "PyJWT"
inherit pypi

RDEPENDS_${PN} = "${PYTHON_PN}-cryptography"

BBCLASSEXTEND = "native nativesdk"
