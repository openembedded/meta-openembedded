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

SRC_URI[sha256sum] = "82c5c05585e70b6ba2d3ae09ea60b79548872185d2f24ae1f2709d37299fd607"

BBCLASSEXTEND = "native nativesdk"
