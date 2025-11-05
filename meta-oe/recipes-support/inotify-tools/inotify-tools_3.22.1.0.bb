SUMMARY = "Command line tools and C library providing a simple interface to inotify"
AUTHOR = "Rohan McGovern <rohan@mcgovern.id.au>"
HOMEPAGE = "http://wiki.github.com/rvoicilas/inotify-tools"
SECTION = "console/devel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=ac6c26e52aea428ee7f56dc2c56424c6"

SRCREV = "4b72dcd15299e1d0cb096397d9aab513ffed1657"

SRC_URI = "git://github.com/${BPN}/${BPN};branch=master;protocol=https \
           file://0002-libinotifytools-Bridge-differences-between-musl-glib.patch \
           file://run-ptest \
          "

S = "${WORKDIR}/git"

inherit autotools ptest

EXTRA_OECONF = "--disable-doxygen"

# workaround until glibc 2.35 is fixed for this [1]
# [1] https://sourceware.org/pipermail/libc-alpha/2021-December/134215.html
CFLAGS += "-Wno-error"

PACKAGES =+ "libinotifytools"

FILES:libinotifytools = "${libdir}/lib*.so.*"

do_compile_ptest() {
        cd libinotifytools/src
        oe_runmake
        oe_runmake test
}

do_install_ptest() {
    install -d ${D}${PTEST_PATH}
    cp -r ${B}/libinotifytools/src/.libs/test ${D}${PTEST_PATH}/
}

FILES:${PN}-ptest += "${PTEST_PATH}"
