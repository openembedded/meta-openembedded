SUMMARY = "Python library implementing ASN.1 types."
HOMEPAGE = "http://pyasn1.sourceforge.net/"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=280606d9c18f200e03e0c247ac61475a"

SRC_URI[md5sum] = "d58ecd0d9ff07218073c5acb12974254"
SRC_URI[sha256sum] = "187f2a66d617683f8e82d5c00033b7c8a0287e1da88a9d577aebec321cad4965"

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_PN}-codecs \
    ${PYTHON_PN}-lang \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-math \
    ${PYTHON_PN}-shell \
"

BBCLASSEXTEND = "native nativesdk"
