SUMMARY = "C++11 command line parser"
DESCRIPTION = "A command line parser for C++11 and beyond that provides a rich feature set with a simple and intuitive interface."
HOMEPAGE = "https://github.com/CLIUtils/CLI11"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b8bdde6bda8508bef68a39f3e0d7e939"

DEPENDS = "catch2"

SRC_URI = "gitsm://github.com/CLIUtils/CLI11;branch=main;protocol=https \
           file://remove_tmpdir_from_test_binary.patch \
           file://run-ptest"
SRCREV = "4160d259d961cd393fd8d67590a8c7d210207348"

inherit cmake ptest

# cli11 is a header only C++ library, so the main package will be empty.
RDEPENDS:${PN}-dev = ""
RDEPENDS:${PN}-ptest = ""

do_install_ptest(){
	# double tests is not a typo. The inner empty tests folder is
	# used by one the tests.
	install -d ${D}${PTEST_PATH}/tests/tests
	for t in `ls ${B}/tests/*Test`; do
		install $t ${D}${PTEST_PATH}/tests/
	done
	install ${B}/tests/ensure_utf8 ${D}${PTEST_PATH}/tests/
	install ${B}/tests/ensure_utf8_twice ${D}${PTEST_PATH}/tests/
	install ${B}/tests/link_test_2 ${D}${PTEST_PATH}/tests/
}

BBCLASSEXTEND = "native nativesdk"
