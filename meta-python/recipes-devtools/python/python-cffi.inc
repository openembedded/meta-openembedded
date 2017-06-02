SUMMARY = "Foreign Function Interface for Python calling C code"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5677e2fdbf7cdda61d6dd2b57df547bf"
DEPENDS += "libffi ${PYTHON_PN}-pycparser"

SRC_URI[md5sum] = "2b5fa41182ed0edaf929a789e602a070"
SRC_URI[sha256sum] = "b3b02911eb1f6ada203b0763ba924234629b51586f72a21faacc638269f4ced5"

RDEPENDS_${PN}_class-target = " \
    ${PYTHON_PN}-io \
"

BBCLASSEXTEND = "native nativesdk"
