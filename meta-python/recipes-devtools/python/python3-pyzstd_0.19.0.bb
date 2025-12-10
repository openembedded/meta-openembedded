DESCRIPTION = "Pyzstd module provides classes and functions for compressing and \
decompressing data, using Facebook’s Zstandard (or zstd as short name) algorithm."
HOMEPAGE = "https://github.com/Rogdham/pyzstd"
SECTION = "devel/python"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=aedb5a2679cd1552fb61c181ef974b9e"

PYPI_PACKAGE = "pyzstd"

SRC_URI[sha256sum] = "44e7b9be7b445aac742003ddee52ca31385d52dffb8c60adf633fad8e795f10d"

inherit pypi python_setuptools_build_meta ptest-python-pytest python_hatchling

DEPENDS += "python3-hatch-vcs-native"

RDEPENDS:${PN}-ptest += "python3-backports-zstd"
