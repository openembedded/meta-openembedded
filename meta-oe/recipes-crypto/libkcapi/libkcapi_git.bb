SUMMARY = "Linux Kernel Crypto API User Space Interface Library"
HOMEPAGE = "http://www.chronox.de/libkcapi.html"
LICENSE = "BSD | GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=14d5a68b28755c04ebdba226e888b157"

DEPENDS = "libtool"

S = "${WORKDIR}/git"
SRCREV = "3c56934f44a8f5a1257c342942e6e034fc6f20be"
PV = "1.1.4+git${SRCPV}"
SRC_URI = " \
    git://github.com/smuellerDD/libkcapi.git \
"

inherit autotools

PACKAGECONFIG ??= ""
PACKAGECONFIG[testapp] = "--enable-kcapi-test,,,bash"
PACKAGECONFIG[apps] = "--enable-kcapi-speed --enable-kcapi-hasher --enable-kcapi-rngapp --enable-kcapi-encapp --enable-kcapi-dgstapp,,,"

do_install_append() {
    # bindir contains testapp and apps.  However it is always created, even
    # when no binaries are installed (empty bin_PROGRAMS in Makefile.am),
    rmdir --ignore-fail-on-non-empty ${D}${bindir}

    # Remove the generated binary checksum files
    rm -f ${D}${bindir}/.*.hmac
    rm -f ${D}${libdir}/.*.hmac
}

CPPFLAGS_append_libc-musl_toolchain-clang = " -Wno-error=sign-compare"
