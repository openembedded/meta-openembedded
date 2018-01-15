SUMMARY = "Foreign Function Interface for Python calling C code"
HOMEPAGE = "http://cffi.readthedocs.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5677e2fdbf7cdda61d6dd2b57df547bf"
DEPENDS += "libffi ${PYTHON_PN}-pycparser"

SRC_URI[md5sum] = "8ef0f852498908b20bc508799901fe93"
SRC_URI[sha256sum] = "df9083a992b17a28cd4251a3f5c879e0198bb26c9e808c4647e0a18739f1d11d"

RDEPENDS_${PN}_class-target = " \
    ${PYTHON_PN}-ctypes \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-shell \
    ${PYTHON_PN}-subprocess \
"

BBCLASSEXTEND = "native nativesdk"
