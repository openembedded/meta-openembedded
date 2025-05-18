# SPDX-License-Identifier: MIT
# Copyright (C) 2023 iris-GmbH infrared & intelligent sensors

SUMMARY = "A single module, which implements a platform independent file lock in Python, which provides a simple way of inter-process communication"
HOMEPAGE = "https://py-filelock.readthedocs.io/"
LICENSE = "Unlicense"
LIC_FILES_CHKSUM = "file://LICENSE;md5=911690f51af322440237a253d695d19f"

SRC_URI[sha256sum] = "81de9eb8453c769b63369f87f11131a7ab04e367f8d97ad39dc230daa07e3bec"

BBCLASSEXTEND = "native nativesdk"
inherit pypi python_hatchling

DEPENDS += "\
    python3-hatch-vcs-native \
"

RDEPENDS:${PN} += " \
    python3-core \
    python3-logging \
    python3-asyncio \
"
