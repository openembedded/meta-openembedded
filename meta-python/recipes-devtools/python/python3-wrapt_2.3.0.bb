SUMMARY = "A Python module for decorators, wrappers and monkey patching."
HOMEPAGE = "https://wrapt.readthedocs.org/"
LICENSE = "BSD-2-Clause"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=63a78af2900bfcc5ce482f3b8d445898"

inherit pypi python_setuptools_build_meta ptest-python-pytest

SRC_URI[sha256sum] = "681a2d0eefd721998f90642762b8e75c2159ec531b20ad5e437245ea7b06a107"

# python3-misc for 'this' module
RDEPENDS:${PN}-ptest += " \
	python3-misc \
"

RDEPENDS:${PN}:append:class-target = " \
    python3-stringold \
    python3-threading \
"

BBCLASSEXTEND = "native"
