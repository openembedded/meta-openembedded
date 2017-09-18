SUMMARY = "Foreign Function Interface for Python calling C code"
HOMEPAGE = "http://cffi.readthedocs.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5677e2fdbf7cdda61d6dd2b57df547bf"
DEPENDS += "libffi ${PYTHON_PN}-pycparser"

SRC_URI[md5sum] = "2c5939cc2fa0183fe0c2fcb6a4f1994d"
SRC_URI[sha256sum] = "5f4ff33371c6969b39b293d9771ee91e81d26f9129be093ca1b7be357fcefd15"

RDEPENDS_${PN}_class-target = " \
    ${PYTHON_PN}-io \
"

BBCLASSEXTEND = "native nativesdk"
