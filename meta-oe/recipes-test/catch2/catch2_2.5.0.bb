DESCRIPTION = "A modern, C++-native, header-only, test framework for unit-tests, \
TDD and BDD - using C++11, C++14, C++17 and later."
AUTHOR = "Phil Nash, Martin Horenovsky and others"
HOMEPAGE = "https://github.com/catchorg/Catch2"
LICENSE = "BSL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=e4224ccaecb14d942c71d31bef20d78c"

SRC_URI = "git://github.com/catchorg/Catch2.git \
           file://0001-Remove-redundant-move.patch \
          "
# v2.5.0
#SRCREV = "6ccd467094973824d89efb16cbc553e279f79823"
SRCREV = "461843b1f02a4bf5c59db88ff201d2c56fabf3a4"

S = "${WORKDIR}/git"

inherit cmake python3native

do_install_append() {
    rm ${D}${datadir}/Catch2/lldbinit
    rm ${D}${datadir}/Catch2/gdbinit
    rmdir ${D}${datadir}/Catch2/
}
# Header-only library
RDEPENDS_${PN}-dev = ""
RRECOMMENDS_${PN}-dbg = "${PN}-dev (= ${EXTENDPKGV})"
