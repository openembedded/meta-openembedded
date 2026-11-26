SUMMARY = "toml config parser and serializer for c++."
HOMEPAGE = "https://github.com/marzer/tomlplusplus"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=90960f22c10049c117d56ed2ee5ee167"

SRC_URI = "git://github.com/marzer/tomlplusplus.git;protocol=https;branch=master;tag=v${PV} \
           file://run-ptest \
"

PV = "3.5.1"
SRCREV = "efd80e03356af65c7d4e61c56ffb326b34f8d0f4"
UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)+)$"

DEPENDS = "cmake-native"

inherit meson ptest pkgconfig

EXTRA_OEMESON += "-Dregenerate=false -Dbuild_lib=true -Dbuild_tests=${@bb.utils.contains("DISTRO_FEATURES", "ptest", "true", "false", d)} \
"

# See - https://github.com/marzer/tomlplusplus/issues/279
CXXFLAGS:append:toolchain-clang = " -stdlib=libstdc++"
LDFLAGS:append:toolchain-clang = " -stdlib=libstdc++"

do_install_ptest () {
    install -d ${D}${PTEST_PATH}/tests
    cp ${B}/tests/tomlplusplus_odr_test ${D}${PTEST_PATH}/tests
    cp ${B}/tests/tomlplusplus_tests ${D}${PTEST_PATH}/tests
}

# musl's strtod() leaves floating-point exception flags set, which the
# float round-trip test checks for
do_install_ptest:append:libc-musl () {
    sed -i -e 's|^args=""$|args="~round trips - floats"|' ${D}${PTEST_PATH}/run-ptest
}
