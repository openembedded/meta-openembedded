# SPDX-License-Identifier: MIT
# Copyright (C) 2023 iris-GmbH infrared & intelligent sensors

SUMMARY = "A tool for creating isolated virtual python environments."
HOMEPAGE = "https://github.com/pypa/virtualenv"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0ce089158cf60a8ab6abb452b6405538"

SRC_URI[sha256sum] = "a85caa554ced0c0afbd0d638e7e2d7b5f92d23478d05d17a76daeac8f279f924"

BBCLASSEXTEND = "native nativesdk"
inherit pypi python_hatchling

DEPENDS += "\
    ${PYTHON_PN}-hatch-vcs-native \
"

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-distlib \
    ${PYTHON_PN}-filelock \
    ${PYTHON_PN}-platformdirs \
"
