SUMMARY = "Extensible memoizing collections and decorators"
HOMEPAGE = "https://github.com/tkem/cachetools"
DESCRIPTION = "This module provides various memoizing \
collections and decorators, including variants of the \
Python 3 Standard Library @lru_cache function decorator."
SECTION = "devel/python"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e21bbe53b2730bfe1911cf381b81821e"

inherit pypi python_setuptools_build_meta ptest-python-pytest

RDEPENDS:${PN} += " \
	python3-math \
"

SRC_URI[sha256sum] = "f225782b84438f828328fc2ad74346522f27e5b1440f4e9fd18b20ebfd1aa2cf"

BBCLASSEXTEND = "native nativesdk"
