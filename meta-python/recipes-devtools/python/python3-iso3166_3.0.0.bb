SUMMARY = "Self-contained ISO 3166-1 country definitions"
HOMEPAGE = "https://pypi.org/project/iso3166/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=5e2f4edc7e7408a82e4a1d05f229b695"

SRC_URI += "file://0001-pyproject.toml-Relax-the-uv_build-version-bound.patch"
SRC_URI[sha256sum] = "e567b55d169211d4d499c73307350da15df5d38248e60bae559609570c42ef55"

inherit pypi python_uv_build ptest-python-pytest

DEPENDS += "python3-wheel-native"

RDEPENDS:${PN} += "python3-numbers"

BBCLASSEXTEND = "native nativesdk"
