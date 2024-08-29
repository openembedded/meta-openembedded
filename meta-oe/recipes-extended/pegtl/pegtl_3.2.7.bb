DESCRIPTION="header-only library for creating parsers according to Parsing Expression Grammar"
HOMEPAGE="https://github.com/taocpp/PEGTL"
LICENSE="MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dccf35ef30bf912bb07b01d469965293"

SRC_URI = " \
	git://github.com/taocpp/PEGTL.git;protocol=https;branch=3.x \
	file://run-ptest \
"

SRCREV = "cf639f7f4ee125f68e1ccfba8d99ebc0de57b9fe"

inherit cmake ptest

S = "${WORKDIR}/git"

do_install_ptest () {
    install -d ${D}${PTEST_PATH}/src/test/pegtl/data
    install -m 0755 ${B}/src/test/pegtl/pegtl-test-* ${D}${PTEST_PATH}/src/test/pegtl
    install ${S}/src/test/pegtl/file_*.txt ${D}${PTEST_PATH}/src/test/pegtl
    install ${S}/src/test/pegtl/data/*.json ${D}${PTEST_PATH}/src/test/pegtl/data
}

CXXFLAGS += " -Wno-error=type-limits"
