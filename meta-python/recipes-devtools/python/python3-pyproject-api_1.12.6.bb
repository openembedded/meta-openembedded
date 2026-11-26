# SPDX-License-Identifier: MIT
# Copyright (C) 2023 iris-GmbH infrared & intelligent sensors

SUMMARY = "pyproject-api aims to abstract away interaction with pyproject.toml style projects in a flexible way."
HOMEPAGE = "https://pyproject-api.readthedocs.io"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=11610a9d8fd95649cf8159be12b98cb7"

SRC_URI[sha256sum] = "422f037b711cebc650241102a83496aeee6eba088597ce84b58b473760740ad7"

BBCLASSEXTEND = "native nativesdk"
inherit pypi python_hatchling

DEPENDS += "\
    python3-hatch-fancy-pypi-readme-native \
    python3-hatch-vcs-native \
    python3-python-sbom-native \
"

RDEPENDS:${PN} += "\
    python3-packaging \
    python3-tomli \
"
