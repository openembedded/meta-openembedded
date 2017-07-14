SUMMARY = "Trace and analyze execution of a program written in C/C++"
HOMEPAGE = "https://github.com/namhyung/uftrace"
BUGTRACKER = "https://github.com/namhyung/uftrace/issues"
SECTION = "devel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "elfutils"
DEPENDS_append_libc-musl = " argp-standalone"

inherit autotools

SRCREV = "712ad01fdde57893936d7e254451eec67ab41ca6"
SRC_URI = "\
    git://github.com/namhyung/${BPN} \
    file://0001-Makefile-Add-LDFLAGS-in-export.patch \
    file://0002-utils-Add-limits-header-to-fix-build-error.patch \
"
S = "${WORKDIR}/git"

LDFLAGS_append_libc-musl = " -largp"
EXTRA_OECONF = "ARCH=${TARGET_ARCH}"

do_configure() {
    ${S}/configure ${EXTRA_OECONF}
}

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"

COMPATIBLE_HOST = "(x86_64|aarch64|arm)"

# uftrace supports armv6 and above
COMPATIBLE_HOST_armv4 = 'null'
COMPATIBLE_HOST_armv5 = 'null'
