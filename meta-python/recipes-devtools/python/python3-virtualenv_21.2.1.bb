# SPDX-License-Identifier: MIT
# Copyright (C) 2023 iris-GmbH infrared & intelligent sensors

SUMMARY = "A tool for creating isolated virtual python environments."
HOMEPAGE = "https://github.com/pypa/virtualenv"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0ce089158cf60a8ab6abb452b6405538"

SRC_URI[sha256sum] = "b66ffe81301766c0d5e2208fc3576652c59d44e7b731fc5f5ed701c9b537fa78"

SRC_URI += " \
    file://CVE-2026-102938.patch \
    file://CVE-2026-102930-dependent.patch \
    file://CVE-2026-102930_p1.patch \
    file://CVE-2026-102930_p2.patch \
    file://CVE-2026-102930_p3.patch \
    file://CVE-2026-102925.patch \
    file://CVE-2026-113732_p1.patch \
    file://CVE-2026-113732_p2.patch \
    file://CVE-2026-113776.patch \
    file://CVE-2026-113726-dependent.patch \
    file://CVE-2026-113726.patch \
"

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
    python3-packaging \
    python3-platformdirs \
"

CVE_PRODUCT = "virtualenv"
