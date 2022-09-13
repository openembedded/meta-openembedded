SUMMARY = "The xmlschema library is an implementation of XML Schema for Python (supports Python 3.6+)."
HOMEPAGE = "https://github.com/sissaschool/xmlschema"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=47489cb18c469474afeb259ed1d4832f"

SRC_URI[sha256sum] = "00d34f97c761c191c20e822899c490930d2c4c9ea6cc7e98f25360fde498f1ab"

PYPI_PACKAGE = "xmlschema"
inherit pypi setuptools3

DEPENDS += "\
    ${PYTHON_PN}-elementpath-native \
"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-elementpath \
    ${PYTHON_PN}-modules \
"

BBCLASSEXTEND = "native nativesdk"
