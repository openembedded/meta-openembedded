SUMMARY = "Extensible memoizing collections and decorators"
HOMEPAGE = "https://github.com/tkem/cachetools"
DESCRIPTION = "This module provides various memoizing \
collections and decorators, including variants of the \
Python 3 Standard Library @lru_cache function decorator."
SECTION = "devel/python"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=079933dfba36eb60b5e3512ca0ab61ae"

inherit pypi python_setuptools_build_meta ptest-python-pytest

RDEPENDS:${PN} += " \
	python3-math \
"

SRC_URI[sha256sum] = "6d8bfbba1ba94412fb9d9196c4da7a87e9d4928fffc5e93542965dca4740c77f"

BBCLASSEXTEND = "native nativesdk"
