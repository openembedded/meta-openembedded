SUMMARY = "Backport of compression.zstd"
HOMEPAGE = "https://github.com/rogdham/backports.zstd"
LICENSE = "0BSD & PSF-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=224f30639105a6ab845e068c2d0903ff \
                    file://LICENSE_zstd.txt;md5=85fffd6822a26cd7d2a6eeb939ada0da \
                    file://src/c/pythoncapi-compat/COPYING;md5=f74f54822fab8814a50330e4e4578b88 \
                    file://src/c/zstd/LICENSE;md5=0822a32f7acdbe013606746641746ee8"

inherit pypi python_setuptools_build_meta ptest-python-pytest

SRC_URI[sha256sum] = "e8b2d68e2812f5c9970cabc5e21da8b409b5ed04e79b4585dbffa33e9b45ebe2"

PYPI_PACKAGE = "backports_zstd"

LDFLAGS:append:toolchain-clang = " -fuse-ld=lld"
DEPENDS:append:toolchain-clang = " lld-native"

RDEPENDS:${PN}-ptest += "bash python3-compile python3-codecs python3-mypy"
