DESCRIPTION = "Utilities for interacting with PyPI"
HOMEPAGE = "https://twine.readthedocs.io/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a3d1106b253a8d50dd82a4202a045b4c"

SRC_URI[sha256sum] = "101f1c43ea4587ab19261d09157c63b6f37a0e55b215f0dd26304df1d49b620c"

inherit pypi setuptools3

DEPENDS += "\
    ${PYTHON_PN}-setuptools-scm-native \
"

BBCLASSEXTEND = "native"
