SUMMARY = "Foreign Function Interface for Python calling C code"
HOMEPAGE = "http://cffi.readthedocs.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5677e2fdbf7cdda61d6dd2b57df547bf"
DEPENDS += "libffi ${PYTHON_PN}-pycparser"

SRC_URI[md5sum] = "4d7dcb6c7c738c15d2ece9bd4c5f86da"
SRC_URI[sha256sum] = "e113878a446c6228669144ae8a56e268c91b7f1fafae927adc4879d9849e0ea7"

RDEPENDS_${PN}_class-target = " \
    ${PYTHON_PN}-ctypes \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-shell \
"

BBCLASSEXTEND = "native nativesdk"
