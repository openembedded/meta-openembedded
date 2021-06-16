SUMMARY = "An abstract syntax tree for Python with inference support."
HOMEPAGE = "https://pypi.python.org/pypi/astroid"
SECTION = "devel/python"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a70cf540abf41acb644ac3b621b2fad1"

SRC_URI[sha256sum] = "2476b7f0d6cec13f4c1f53b54bea2ce072310ac9fc7acb669d5270190c748042"

inherit pypi setuptools3

DEPENDS += "${PYTHON_PN}-pytest-runner-native"

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
