SUMMARY = "Extensible memoizing collections and decorators"
HOMEPAGE = "https://github.com/tkem/cachetools"
DESCRIPTION = "This module provides various memoizing \
collections and decorators, including variants of the \
Python 3 Standard Library @lru_cache function decorator."
SECTION = "devel/python"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=539275e657c6b7af026bb908356f7541"

inherit pypi python_setuptools_build_meta ptest

SRC_URI += " \
	file://run-ptest \
"

RDEPENDS:${PN} += " \
	python3-math \
"

RDEPENDS:${PN}-ptest += " \
    python3-pytest \
    python3-unittest-automake-output \
"

do_install_ptest() {
	install -d ${D}${PTEST_PATH}/tests
	cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

SRC_URI[sha256sum] = "b8adc2e7c07f105ced7bc56dbb6dfbe7c4a00acce20e2227b3f355be89bc6827"

BBCLASSEXTEND = "native nativesdk"
