SUMMARY = "Implementation of bounded Levenshtein distance (Ukkonen)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7e49a187324d5a1a6c4ba2b9c3fd4033"

PYPI_PACKAGE = "ukkonen"

inherit pypi setuptools3
SRC_URI[sha256sum] = "976ad9a991c9cb99a7c318695a5f6b46236bbd4c33c5b55273fa241dfca8e0fa"

DEPENDS += " \
	python3-pip-native \
	python3-cffi-native \
"

RDEPENDS:${PN} = " \
	python3-cffi \
"
