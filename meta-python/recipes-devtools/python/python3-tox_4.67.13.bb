# SPDX-License-Identifier: MIT
# Copyright (C) 2023 iris-GmbH infrared & intelligent sensors

SUMMARY = "Automate and standardize testing in Python. It is part of a larger vision of easing the packaging, testing and release process of Python software (alongside pytest and devpi)."
HOMEPAGE = "https://tox.readthedocs.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=11610a9d8fd95649cf8159be12b98cb7"

SRC_URI[sha256sum] = "9f70b44ca2e31d91d8fe2e2917f505554950adc3961c0ee70bb42db16d903f62"

BBCLASSEXTEND = "native nativesdk"
inherit pypi python_hatchling

DEPENDS += "\
    python3-hatch-fancy-pypi-readme-native \
    python3-hatch-vcs-native \
    python3-docutils-native \
"

RDEPENDS:${PN} += "\
    python3-cachetools \
    python3-chardet \
    python3-colorama \
    python3-filelock \
    python3-packaging \
    python3-platformdirs \
    python3-pluggy \
    python3-pyproject-api \
    python3-tomli \
    python3-virtualenv \
"

# Install all built-in python3 modules, as the software tested with tox might
# depend on it. Tox will attempt to install all required dependencies
# in a virtualenv using pip, but this obviously does not include the built-in modules.
RDEPENDS:${PN} += "python3-modules"
