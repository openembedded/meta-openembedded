SUMMARY = "Python library implementing ASN.1 types."
HOMEPAGE = "http://pyasn1.sourceforge.net/"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=280606d9c18f200e03e0c247ac61475a"

SRC_URI[md5sum] = "800d0114db2084f7256586dadf37d1aa"
SRC_URI[sha256sum] = "6b42f96b942406712e0be5ea2bbbc57d8f30c7835a4904c9c195cc669736d435"

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_PN}-lang \
    ${PYTHON_PN}-shell \
"

BBCLASSEXTEND = "native nativesdk"
