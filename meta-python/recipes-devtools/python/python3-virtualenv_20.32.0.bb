# SPDX-License-Identifier: MIT
# Copyright (C) 2023 iris-GmbH infrared & intelligent sensors

SUMMARY = "A tool for creating isolated virtual python environments."
HOMEPAGE = "https://github.com/pypa/virtualenv"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0ce089158cf60a8ab6abb452b6405538"

SRC_URI[sha256sum] = "886bf75cadfdc964674e6e33eb74d787dff31ca314ceace03ca5810620f4ecf0"

BBCLASSEXTEND = "native nativesdk"
inherit pypi python_hatchling

DEPENDS += "\
    python3-hatch-vcs-native \
"

RDEPENDS:${PN} += " \
    python3-compression  \
    python3-distlib \
    python3-filelock \
    python3-json \
    python3-misc \
    python3-modules \
    python3-platformdirs \
"
