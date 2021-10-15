SUMMARY = "SpiderMonkey is Mozilla's JavaScript engine written in C/C++"
HOMEPAGE = "https://developer.mozilla.org/en-US/docs/Mozilla/Projects/SpiderMonkey"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dc9b6ecd19a14a54a628edaaf23733bf"

SRC_URI = "https://archive.mozilla.org/pub/firefox/releases/${PV}esr/source/firefox-${PV}esr.source.tar.xz \
           file://0001-Cargo.toml-do-not-abort-on-panic.patch \
           file://0002-moz.configure-do-not-look-for-llvm-objdump.patch \
           file://0003-rust.configure-do-not-try-to-find-a-suitable-upstrea.patch \
           file://0001-build-do-not-use-autoconf-s-config.sub-to-canonicali.patch \
           "
SRC_URI[sha256sum] = "3b913d6a52c95a8986123c98543f1273812a2d59f2891b4b1c604e35f4580766"

S = "${WORKDIR}/firefox-${@d.getVar("PV").replace("esr", "")}"

inherit pkgconfig perlnative python3native rust

DEPENDS += "zlib cargo-native python3"

B = "${WORKDIR}/build"

export PYTHONPATH = "${S}/build:${S}/third_party/python/PyYAML/lib3:${S}/testing/mozbase/mozfile:${S}/python/mozboot:${S}/third_party/python/distro:${S}/testing/mozbase/mozinfo:${S}/config:${S}/testing/mozbase/manifestparser:${S}/third_party/python/pytoml:${S}/testing/mozbase/mozprocess:${S}/third_party/python/six:${S}/python/mozbuild:${S}/python/mozbuild/mozbuild:${S}/python/mach:${S}/third_party/python/jsmin:${S}/python/mozversioncontrol"

export HOST_CC = "${BUILD_CC}"
export HOST_CXX = "${BUILD_CXX}"
export HOST_CFLAGS = "${BUILD_CFLAGS}"
export HOST_CPPFLAGS = "${BUILD_CPPFLAGS}"
export HOST_CXXFLAGS = "${BUILD_CXXFLAGS}"

export AS = "${CC}"

export RUSTFLAGS

JIT ?= ""

JIT:mipsarch = "--disable-jit"

do_configure() {
    cd ${B}
    python3 ${S}/configure.py \
        --enable-project=js \
        --target=${HOST_SYS} \
        --host=${BUILD_SYS} \
        --prefix=${prefix} \
        --libdir=${libdir} \
        --disable-jemalloc \
        ${JIT}

}

do_install() {
    oe_runmake 'DESTDIR=${D}' install
}

inherit multilib_script multilib_header

MULTILIB_SCRIPTS += " ${PN}-dev:${bindir}/js91-config"

do_install:append() {
       oe_multilib_header mozjs-91/js-config.h
       sed -e 's@${STAGING_DIR_HOST}@@g' \
           -i ${D}${bindir}/js91-config
}

PACKAGES =+ "lib${BPN}"
FILES:lib${BPN} += "${libdir}/lib*"
