SUMMARY = "Library for building powerful interactive command lines in Python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b2cde7da89f0c1f3e49bf968d00d554f"

SRC_URI[md5sum] = "f24061ae133ed32c6b764e92bd48c496"
SRC_URI[sha256sum] = "cc66413b1b4b17021675d9f2d15d57e640b06ddfd99bb724c73484126d22622f"

PYPI_PACKAGE = "prompt_toolkit"

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-core \
    ${PYTHON_PN}-six \
    ${PYTHON_PN}-subprocess \
    ${PYTHON_PN}-terminal \
    ${PYTHON_PN}-threading \
    ${PYTHON_PN}-wcwidth \
"

BBCLASSEXTEND = "native nativesdk"
