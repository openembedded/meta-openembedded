SUMMARY = "A wrapper for the Gnu Privacy Guard (GPG or GnuPG)"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=5dabe659eadd6d97325b1582e41cfc11"

PYPI_PACKAGE = "python-gnupg"

SRC_URI += "file://0001-pyproject.toml-Relax-the-setuptools-version-bound.patch"
SRC_URI[sha256sum] = "73ea46219f992b361eb1ce54cb0968101670654454916a3ee5df8bb9bf0cc8cc"

CVE_PRODUCT = "python:python-gnupg"

inherit pypi python_setuptools_build_meta


DEPENDS += "python3-wheel-native"

RDEPENDS:${PN} +=  " \
	gnupg-gpg \
	python3-logging \
"
