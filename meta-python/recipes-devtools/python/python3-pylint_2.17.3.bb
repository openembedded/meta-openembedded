SUMMARY="Pylint is a Python source code analyzer"
HOMEPAGE= "http://www.pylint.org/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c107cf754550e65755c42985a5d4e9c9"

SRC_URI += "file://0001-pyproject.toml-Remove-hard-coded-dependencies.patch"
SRC_URI[sha256sum] = "761907349e699f8afdcd56c4fe02f3021ab5b3a0fc26d19a9bfdc66c7d0d5cd5"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "${PYTHON_PN}-astroid \
                   ${PYTHON_PN}-isort \
                   ${PYTHON_PN}-numbers \
                   ${PYTHON_PN}-shell \
                   ${PYTHON_PN}-json \
                   ${PYTHON_PN}-pkgutil \
                   ${PYTHON_PN}-difflib \
                   ${PYTHON_PN}-netserver \
                  "
