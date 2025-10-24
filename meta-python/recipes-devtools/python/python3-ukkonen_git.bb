SUMMARY = "Implementation of bounded Levenshtein distance (Ukkonen)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7e49a187324d5a1a6c4ba2b9c3fd4033"

PYPI_PACKAGE = "ukkonen"

inherit setuptools3

PV = "1.0.1+git"
SRC_URI += "git://github.com/asottile/ukkonen;protocol=https;branch=main"
SRCREV = "ad108a16e8203867f4860287b61149e0bdd838ca"
DEPENDS += " \
	python3-pip-native \
	python3-cffi-native \
"

RDEPENDS:${PN} = " \
	python3-cffi \
"
