SUMMARY = "YANG data modeling language library"
DESCRIPTION = "libyang is a YANG data modelling language parser and toolkit written (and providing API) in C."
HOMEPAGE = "https://github.com/CESNET/libyang"
SECTION = "libs"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://LICENSE;md5=f3916d7d8d42a6508d0ea418cfff10ad"

SRCREV = "35131b9396a965e01f899127763fb4e0871b845a"

SRC_URI = "git://github.com/CESNET/libyang.git;branch=master;protocol=https \
           file://run-ptest \
           "

S = "${WORKDIR}/git"

# Main dependencies
inherit cmake pkgconfig lib_package ptest
DEPENDS = "libpcre2"
DEPENDS += "${@bb.utils.contains('PTEST_ENABLED', '1', 'cmocka', '', d)}"

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release"
EXTRA_OECMAKE += " ${@bb.utils.contains('PTEST_ENABLED', '1', '-DENABLE_TESTS=ON -DENABLE_VALGRIND_TESTS=OFF', '', d)}"

do_compile:prepend () {
    if [ ${PTEST_ENABLED} = "1" ]; then
        sed -i -e 's|${S}|${PTEST_PATH}|g' ${B}/tests/tests_config.h
        sed -i -e 's|${B}|${PTEST_PATH}|g' ${B}/tests/tests_config.h
    fi
}

do_install_ptest () {
    install -d ${D}${PTEST_PATH}/tests
    cp -f ${B}/tests/utest_* ${D}${PTEST_PATH}/tests/
    cp -fR ${S}/tests/modules ${D}${PTEST_PATH}/tests/
    install -d ${D}${PTEST_PATH}/tests/plugins
    cp -f ${B}/tests/plugins/plugin_*.so ${D}${PTEST_PATH}/tests/plugins/
}

FILES:${PN} += "${datadir}/yang/*"
