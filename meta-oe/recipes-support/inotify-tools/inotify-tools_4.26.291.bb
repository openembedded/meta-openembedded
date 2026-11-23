SUMMARY = "Command line tools and C library providing a simple interface to inotify"
HOMEPAGE = "https://github.com/inotify-tools/inotify-tools"
SECTION = "console/devel"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=ac6c26e52aea428ee7f56dc2c56424c6"

SRC_URI = "https://github.com/${BPN}/${BPN}/releases/download/${PV}/${BP}.tar.gz \
           file://run-ptest \
           "
SRC_URI[sha256sum] = "633c012d30a0f66770454ee15e6d5d24003a61206b4a698cc4b32e6715855667"

UPSTREAM_CHECK_URI = "https://github.com/${BPN}/${BPN}/releases"
UPSTREAM_CHECK_REGEX = "releases/tag/(?P<pver>\d+(\.\d+)+)"

inherit cargo cargo-update-recipe-crates ptest

require ${BPN}-crates.inc

EXTRA_OEMAKE = " \
    CARGOFLAGS='-v --frozen' \
    PROFILE=${CARGO_PROFILE} \
    TARGET=${RUST_HOST_SYS} \
    CARGO_TARGET_DIR=${B}/target \
    ENABLE_STATIC=0 \
    prefix=${prefix} \
    bindir=${bindir} \
    libdir=${libdir} \
    includedir=${includedir} \
    mandir=${mandir} \
"

do_compile() {
    export RUSTFLAGS="${RUSTFLAGS}"
    oe_runmake -C ${S}
}

do_install() {
    oe_cargo_fix_env
    export RUSTFLAGS="${RUSTFLAGS}"
    oe_runmake -C ${S} install DESTDIR=${D}
}

PACKAGES =+ "libinotifytools"

FILES:libinotifytools = "${libdir}/lib*.so.*"

# The C++ unit test of the C ABI, linked against the installed library
do_compile_ptest() {
    ${CXX} ${CXXFLAGS} -std=c++17 -Wall -I${S}/libinotifytools/src \
        -o ${B}/test ${S}/libinotifytools/src/test.cpp \
        -L${B}/target/${CARGO_TARGET_SUBDIR} -linotifytools ${LDFLAGS}
}

do_install_ptest() {
    install -d ${D}${PTEST_PATH}
    install -m 0755 ${B}/test ${D}${PTEST_PATH}/
}
