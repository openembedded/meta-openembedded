DESCRIPTION = "A flexible forms validation and rendering library for python web development."
HOMEPAGE = "https://pypi.python.org/pypi/WTForms"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"


LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=53dbfa56f61b90215a9f8f0d527c043d"

SRC_URI[sha256sum] = "4abfbaa1d529a1d0ac927d44af8dbb9833afd910e56448a103f1893b0b176886"

PYPI_PACKAGE = "WTForms"

inherit pypi setuptools3

DEPENDS += "\
    ${PYTHON_PN}-pip-native \
    ${PYTHON_PN}-babel-native \
    "

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-netserver \
    ${PYTHON_PN}-numbers \
    ${PYTHON_PN}-markupsafe \
    "
