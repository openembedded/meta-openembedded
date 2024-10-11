DESCRIPTION = "Python data structure and operations for intervals"
HOMEPAGE = "https://github.com/AlexandreDecan/portion"
SECTION = "devel/python"

LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3000208d539ec061b899bce1d9ce9404"

inherit pypi python_setuptools_build_meta ptest

SRC_URI[sha256sum] = "80ae7bab71f4844e3eb33c57954d76a3bd4cb351039b6e260e779325aeb4a487"

SRC_URI += " \
	file://run-ptest \
"

RDEPENDS:${PN} += "\
	python3-sortedcontainers \
"

RDEPENDS:${PN}-ptest += " \
	python3-pytest \
	python3-unittest-automake-output \
"

do_install_ptest() {
	install -d ${D}${PTEST_PATH}/tests
	# This file tests README.md and deleted as redundant.
	rm -f ${S}/tests/test_doc.py
	cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

BBCLASSEXTEND = "native"
