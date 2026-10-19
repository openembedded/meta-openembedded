SUMMARY = "Extensible memoizing collections and decorators"
HOMEPAGE = "https://github.com/tkem/cachetools"
DESCRIPTION = "This module provides various memoizing \
collections and decorators, including variants of the \
Python 3 Standard Library @lru_cache function decorator."
SECTION = "devel/python"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=079933dfba36eb60b5e3512ca0ab61ae"

inherit pypi python_setuptools_build_meta ptest-python-pytest

DEPENDS += " \
	python3-setuptools-scm-native \
"

RDEPENDS:${PN} += " \
	python3-math \
"

SRC_URI[sha256sum] = "e6fafd06de4a5bd915ff7d247588ea497e28c9a568c62db8678d9a5f11515a9a"

BBCLASSEXTEND = "native nativesdk"
