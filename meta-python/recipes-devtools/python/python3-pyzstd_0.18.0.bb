DESCRIPTION = "Pyzstd module provides classes and functions for compressing and \
decompressing data, using Facebook’s Zstandard (or zstd as short name) algorithm."
HOMEPAGE = "https://github.com/animalize/pyzstd"
SECTION = "devel/python"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=aedb5a2679cd1552fb61c181ef974b9e"

PYPI_PACKAGE = "pyzstd"

SRC_URI += "file://0001-Bump-setuptools-dependency-from-74-to-89.patch"
SRC_URI[sha256sum] = "81b6851ab1ca2e5f2c709e896a1362e3065a64f271f43db77fb7d5e4a78e9861"

inherit pypi python_setuptools_build_meta ptest-python-pytest

# clang-16 with -flto segfaults on arm, therefore ignore flto for now
do_configure:append:arm:toolchain-clang() {
    sed -i -e "s|'-flto'|''|" ${S}/setup.py
}
