SUMMARY = "Fast Base64 encoding/decoding in Python"
HOMEPAGE = "https://github.com/mayeut/pybase64"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5042ea9bdb8e0560ebab12bceefcd3f7"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "e7bc140306325a61cb7bd18b78fbf800df6aecaa9928023b7f7425b680a7601e"

BBCLASSEXTEND = "native nativesdk"
