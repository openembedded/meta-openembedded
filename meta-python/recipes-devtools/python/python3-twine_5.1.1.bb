DESCRIPTION = "Utilities for interacting with PyPI"
HOMEPAGE = "https://twine.readthedocs.io/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a3d1106b253a8d50dd82a4202a045b4c"

SRC_URI[sha256sum] = "9aa0825139c02b3434d913545c7b847a21c835e11597f5255842d457da2322db"

inherit pypi python_setuptools_build_meta

DEPENDS += "\
	python3-setuptools-scm-native \
"

RDEPENDS:${PN} += " \
	python3-importlib-metadata \
"

BBCLASSEXTEND = "native"
