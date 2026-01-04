SUMMARY = "A Python module for decorators, wrappers and monkey patching."
HOMEPAGE = "https://wrapt.readthedocs.org/"
LICENSE = "BSD-2-Clause"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=945f689eb1f8c581bb9d635baba5abb5"

inherit pypi python_setuptools_build_meta ptest-python-pytest

SRC_URI[sha256sum] = "9c9c635e78497cacb81e84f8b11b23e0aacac7a136e73b8e5b2109a1d9fc468f"

# python3-misc for 'this' module
RDEPENDS:${PN}-ptest += " \
	python3-misc \
"

RDEPENDS:${PN}:class-target += "\
    python3-stringold \
    python3-threading \
"

BBCLASSEXTEND = "native"
