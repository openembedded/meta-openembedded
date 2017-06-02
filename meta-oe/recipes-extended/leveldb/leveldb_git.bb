SUMMARY = "LevelDB is a fast key-value storage library"
DESCRIPTION = "LevelDB is a fast key-value storage library that provides an ordered mapping from string keys to string values"
HOMEPAGE = "http://leveldb.googlecode.com"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=92d1b128950b11ba8495b64938fc164d"

SRCREV = "a53934a3ae1244679f812d998a4f16f2c7f309a6"
PV = "1.20+git${SRCPV}"

SRC_URI = "git://github.com/google/${BPN}.git \
"

S = "${WORKDIR}/git"

inherit utils

do_compile() {
    # do not use oe_runmake. oe_runmake pass to make compilation arguments and override
    # leveldb makefile variable CFLAGS and broke leveldb build.
    CFLAGS="${CFLAGS}" make || die
}

do_install() {
    install -d ${D}${libdir} ${D}${bindir} ${D}${includedir}/leveldb
    oe_libinstall -C ${B}/out-shared libleveldb ${D}${libdir}
    oe_libinstall -C ${S}/out-static libleveldb ${D}${libdir}
    oe_libinstall -C ${S}/out-static libmemenv ${D}${libdir}
    install -m 0755 ${B}/out-shared/db_bench ${D}${bindir}
    install -m 0755 ${B}/out-static/*_test ${D}${bindir}
    install -m 644 ${S}/include/leveldb/*.h ${D}${includedir}/leveldb/
}

PACKAGES =+ "${PN}-ptest"
FILES_${PN}-ptest = "${bindir}"
