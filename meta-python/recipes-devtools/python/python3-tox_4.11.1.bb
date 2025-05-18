# SPDX-License-Identifier: MIT
# Copyright (C) 2023 iris-GmbH infrared & intelligent sensors

SUMMARY = "Automate and standardize testing in Python. It is part of a larger vision of easing the packaging, testing and release process of Python software (alongside pytest and devpi)."
HOMEPAGE = "http://tox.readthedocs.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=11610a9d8fd95649cf8159be12b98cb7"

SRC_URI[sha256sum] = "8a8cc94b7269f8e43dfc636eff2da4b33a199a4e575b5b086cc51aae24ac4262"

BBCLASSEXTEND = "native nativesdk"
inherit pypi python_hatchling

DEPENDS += "\
    ${PYTHON_PN}-hatch-vcs-native \
"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-cachetools \
    ${PYTHON_PN}-chardet \
    ${PYTHON_PN}-colorama \
    ${PYTHON_PN}-filelock \
    ${PYTHON_PN}-packaging \
    ${PYTHON_PN}-platformdirs \
    ${PYTHON_PN}-pluggy \
    ${PYTHON_PN}-pyproject-api \
    ${PYTHON_PN}-tomli \
    ${PYTHON_PN}-virtualenv \
"

# Install all built-in python3 modules, as the software tested with tox might
# depend on it. Tox will attempt to install all required dependencies
# in a virtualenv using pip, but this obviously does not include the built-in modules.
RDEPENDS:${PN} += "${PYTHON_PN}-modules"
