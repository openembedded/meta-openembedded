SUMMARY = "Linux Kernel Crypto API User Space Interface Library"
HOMEPAGE = "http://www.chronox.de/libkcapi.html"
LICENSE = "BSD | GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=d0421cf231423bda10cea691b613e866"

DEPENDS = "libtool"

S = "${WORKDIR}/git"
# Use v1.1.1 with changes on top for building in OE
SRCREV = "342b50fc9225a991c224126c13c188ad9f1ef9f9"
PV = "1.1.1+git${SRCPV}"
SRC_URI = " \
    git://github.com/smuellerDD/libkcapi.git \
    file://0001-Fix-possible-buffer-overflow-with-strncpy-and-Wstrin.patch \
    file://0002-apps-Disable-Wstringop-truncation-warning-on-false-p.patch \
    file://0003-test-Be-sure-to-terminate-strncpy-copied-string-Wstr.patch \
"

inherit autotools

PACKAGECONFIG ??= ""
PACKAGECONFIG[testapp] = "--enable-kcapi-test,,,"
PACKAGECONFIG[apps] = "--enable-kcapi-speed --enable-kcapi-hasher --enable-kcapi-rngapp --enable-kcapi-encapp --enable-kcapi-dgstapp,,,"

do_install_append() {
    # bindir contains testapp and apps.  However it is always created, even
    # when no binaries are installed (empty bin_PROGRAMS in Makefile.am),
    rmdir --ignore-fail-on-non-empty ${D}${bindir}
}
