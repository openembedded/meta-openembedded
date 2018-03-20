SUMMARY = "An abstract syntax tree for Python with inference support."
HOMEPAGE = "https://pypi.python.org/pypi/astroid"
SECTION = "devel/python"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI[md5sum] = "8d62e34552299ee7334162f9beae17ac"
SRC_URI[sha256sum] = "a92c1197dd496ef2470e73e1c296fc02a719907ee07259744e26a13bda9d4862"

inherit pypi

PACKAGES =+ "${PN}-tests"

FILES_${PN}-tests += " \
    ${PYTHON_SITEPACKAGES_DIR}/astroid/test* \
    ${PYTHON_SITEPACKAGES_DIR}/astroid/__pycache__/test* \
"

RDEPENDS_${PN}_class-target += "\
    ${PYTHON_PN}-distutils \
    ${PYTHON_PN}-lazy-object-proxy \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-six \
    ${PYTHON_PN}-wrapt \
    ${PYTHON_PN}-setuptools \
"

RDEPENDS_${PN}-tests_class-target += "\
    ${PYTHON_PN}-unittest \
    ${PYTHON_PN}-xml \
"
