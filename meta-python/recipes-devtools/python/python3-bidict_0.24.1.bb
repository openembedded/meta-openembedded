SUMMARY = "The bidirectional mapping library for Python."
DESCRIPTION = "The bidirectional mapping library for Python."
HOMEPAGE = "https://bidict.readthedocs.io/"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=faf006af6b1264b57f6cca7283841ac4"

SRC_URI += "file://0001-pyproject.toml-Relax-the-uv_build-version-bound.patch"
SRC_URI[sha256sum] = "4dca6c17f0b01700e9f24359daa5ebabf7be022d99f4cb2a257b6af2a5076c88"

inherit pypi python_uv_build

DEPENDS += "python3-setuptools-scm-native"
