SUMMARY = "JavaScript unobfuscator and beautifier."
HOMEPAGE = "https://beautifier.io/"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=8227180126797a0148f94f483f3e1489"

inherit pypi setuptools3

SRC_URI[sha256sum] = "c738ebc36b47bd94e4ca6dd17a9004c3cc74edad582ca1d60e0e5d5945a63cb9"

PYPI_PACKAGE="jsbeautifier"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-core \
    ${PYTHON_PN}-stringold \
    ${PYTHON_PN}-shell \
"

BBCLASSEXTEND = "native nativesdk"
