SUMMARY = "A minimal INI file parser"
DESCRIPTION = "minIni is a programmer's library to read and write INI files in \
embedded systems. minIni takes little resources, has a deterministic memory \
footprint and can be configured for various kinds of file I/O libraries. minIni \
provides functionality for reading, writing and deleting keys from an INI file, \
all in about 1000 lines of (commented) source code in C (the code also \
compiles in C++ and comes with a wrapper class)."
HOMEPAGE = "https://github.com/compuphase/minIni"

# License is Apache 2.0 with an exception to allow object code built from
# unmodified original sources to be distributed more freely. See LICENSE
# file for details.

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f75fb05044181a4a14fcbf1a88f37fa8"

SRC_URI = "git://github.com/compuphase/minIni.git;protocol=https;branch=master;tag=v${PV} \
           file://0001-BUG-fix-wrong-type-used-in-prototype-for-ini_putbool.patch \
           "
SRCREV = "ede00e17ab3799dd62d00a5885c144810a335495"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)+)$"

# The sources use CRLF line endings; convert them so patches stay plain
inherit dos2unix

do_configure[noexec] = "1"

do_compile() {
    cd ${S}/dev
    ${CC} ${CFLAGS} -fPIC -c minIni.c -o minIni.o
    ${CC} ${LDFLAGS} -shared -Wl,-soname,libminini.so.0 minIni.o -o libminini.so.0.0
}

do_install () {
    cd ${S}/dev

    install -d ${D}${libdir}
    install -m 0644 libminini.so.0.0 ${D}${libdir}/
    ln -s libminini.so.0.0 ${D}${libdir}/libminini.so
    ln -s libminini.so.0.0 ${D}${libdir}/libminini.so.0

    install -d ${D}${includedir}/minini
    install -m 0644 minIni.h ${D}${includedir}/minini/
    install -m 0644 minGlue-stdio.h ${D}${includedir}/minini/minGlue.h
}
