SUMMARY = "MessagePack (de)serializer"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=cd9523181d9d4fbf7ffca52eaa2a5751"

inherit pypi python_setuptools_build_meta ptest-python-pytest

PTEST_PYTEST_DIR = "test"

SRC_URI[sha256sum] = "8e17af38197bf58e7e819041678f6178f4491493f5b8c8580414f40f7c2c3c41"

RDEPENDS:${PN}:append:class-target = " \
    python3-io \
"

BBCLASSEXTEND = "native nativesdk"
