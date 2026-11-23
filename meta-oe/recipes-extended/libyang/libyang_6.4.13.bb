SUMMARY = "YANG data modeling language library"
DESCRIPTION = "libyang is a YANG data modelling language parser and toolkit written (and providing API) in C."
HOMEPAGE = "https://github.com/CESNET/libyang"
SECTION = "libs"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://LICENSE;md5=b69fd3b2815bbf1cef5c97f0eee2519a"

SRCREV = "f7e0cc1bc99ec9280af1010a94ba8dd8ef448f6d"

SRC_URI = "git://github.com/CESNET/libyang.git;branch=master;protocol=https;tag=v${PV} \
           file://0001-test_context-skip-test-case-test_searchdirs.patch \
           file://run-ptest \
           "

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)+)$"

# Main dependencies
inherit cmake pkgconfig lib_package ptest multilib_header
DEPENDS = " \
    libpcre2 \
    xxhash \
"
DEPENDS += "${@bb.utils.contains('PTEST_ENABLED', '1', 'cmocka', '', d)}"

PACKAGECONFIG ??= ""
PACKAGECONFIG[cbor] = "-DCMAKE_DISABLE_FIND_PACKAGE_CBOR=OFF,-DCMAKE_DISABLE_FIND_PACKAGE_CBOR=ON,libcbor"

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release"
EXTRA_OECMAKE += " ${@bb.utils.contains('PTEST_ENABLED', '1', '-DENABLE_TESTS=ON -DENABLE_VALGRIND_TESTS=OFF', '', d)}"

do_compile:prepend () {
    if [ ${PTEST_ENABLED} = "1" ]; then
        sed -i -e 's|${S}|${PTEST_PATH}|g' ${B}/tests/tests_config.h
        sed -i -e 's|${B}|${PTEST_PATH}|g' ${B}/tests/tests_config.h
    fi
}

do_install:append () {
        oe_multilib_header  libyang/ly_config.h
}

do_install_ptest () {
    install -d ${D}${PTEST_PATH}/tests
    cp -f ${B}/tests/utest_* ${D}${PTEST_PATH}/tests/
    cp -fR ${S}/tests/modules ${D}${PTEST_PATH}/tests/
    cp -fR ${S}/modules ${D}${PTEST_PATH}/
    install -d ${D}${PTEST_PATH}/tests/utests/schema_comparison
    cp -fR ${S}/tests/utests/schema_comparison/bc ${S}/tests/utests/schema_comparison/ed \
        ${S}/tests/utests/schema_comparison/local_full_resolved ${S}/tests/utests/schema_comparison/nbc \
        ${D}${PTEST_PATH}/tests/utests/schema_comparison/
    install -d ${D}${PTEST_PATH}/tests/plugins
    cp -f ${B}/tests/plugins/plugin_*.so ${D}${PTEST_PATH}/tests/plugins/
}

FILES:${PN} += "${datadir}/yang/*"
