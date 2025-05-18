# Copyright (C) 2022 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "A Python bindings generator for C/C++ libraries"

HOMEPAGE = "https://www.riverbankcomputing.com/software/sip/"
LICENSE = "GPL-2.0-or-later"
SECTION = "devel"
LIC_FILES_CHKSUM = "file://LICENSE-GPL2;md5=e91355d8a6f8bd8f7c699d62863c7303"

inherit pypi setuptools3 python3native

PYPI_PACKAGE = "sip"
SRC_URI[sha256sum] = "2ed1904820cb661b7207eb1dccfaebec1a5463dcad903ba448ad1945502d089c"

RDEPENDS:${PN} = " \
    python3-core \
    python3-packaging \
    python3-logging \
    python3-tomllib \
    python3-setuptools \
"

BBCLASSEXTEND = "native"
