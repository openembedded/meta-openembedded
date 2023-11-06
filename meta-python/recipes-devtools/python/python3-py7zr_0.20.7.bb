SUMMARY = "Pure Python 7-zip library"
HOMEPAGE = "https://py7zr.readthedocs.io/en/latest/"
LICENSE = "LGPL-2.1-or-later"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4fbd65380cdd255951079008b364516c"

SRC_URI[sha256sum] = "55aa587fb4586c920437f5d6e6595f228beddb321bfee3f5146bb15c2e8e74d5"

inherit pypi python_setuptools_build_meta

DEPENDS += " \
    ${PYTHON_PN}-setuptools-scm-native \
    ${PYTHON_PN}-toml-native \
    ${PYTHON_PN}-wheel-native \
"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-pycryptodomex \
    ${PYTHON_PN}-multivolumefile \
    ${PYTHON_PN}-pybcj \
    ${PYTHON_PN}-inflate64 \
    ${PYTHON_PN}-pyppmd \
    ${PYTHON_PN}-pyzstd \
    ${PYTHON_PN}-brotli \
    ${PYTHON_PN}-multiprocessing \
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-core \
    ${PYTHON_PN}-threading \
"
