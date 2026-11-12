# Copyright (C) 2022 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "A Python bindings generator for C/C++ libraries"

HOMEPAGE = "https://github.com/Python-SIP/sip"
LICENSE = "BSD-2-Clause"
SECTION = "devel"
LIC_FILES_CHKSUM = "file://LICENSE;md5=236276327275fdb261636fb40b18d88d"

inherit pypi python_setuptools_build_meta python3native

SRC_URI[sha256sum] = "e3b5c2d1429a96f2e1fc25b69d4ceb232bc8b7f2395e864c904cec28905642b5"

DEPENDS += "python3-setuptools-scm-native"

RDEPENDS:${PN} = " \
    python3-core \
    python3-packaging \
    python3-logging \
    python3-tomllib \
    python3-setuptools \
"

BBCLASSEXTEND = "native"
