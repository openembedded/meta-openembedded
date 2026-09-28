SUMMARY = "An abstract syntax tree for Python with inference support."
HOMEPAGE = "https://pypi.python.org/pypi/astroid"
SECTION = "devel/python"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a70cf540abf41acb644ac3b621b2fad1"

SRC_URI[sha256sum] = "8cdaf5b7f3f4f39557ae05ed8b0852136b43a04ab686db7d39255b206233677a"

inherit pypi python_setuptools_build_meta

PACKAGES =+ "${PN}-tests"

FILES:${PN}-tests += " \
    ${PYTHON_SITEPACKAGES_DIR}/astroid/test* \
    ${PYTHON_SITEPACKAGES_DIR}/astroid/__pycache__/test* \
"

RDEPENDS:${PN}:append:class-target = " \
    python3-lazy-object-proxy \
    python3-logging \
    python3-wrapt \
    python3-setuptools \
    python3-typing-extensions \
"

RDEPENDS:${PN}-tests:append:class-target = " \
    python3-unittest \
    python3-xml \
"

BBCLASSEXTEND = "native nativesdk"
