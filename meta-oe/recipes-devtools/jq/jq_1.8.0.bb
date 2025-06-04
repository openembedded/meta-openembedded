SUMMARY = "Lightweight and flexible command-line JSON processor"
DESCRIPTION = "jq is like sed for JSON data, you can use it to slice and \
               filter and map and transform structured data with the same \
               ease that sed, awk, grep and friends let you play with text."
HOMEPAGE = "https://jqlang.github.io/jq/"
BUGTRACKER = "https://github.com/jqlang/jq/issues"
SECTION = "utils"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=08ffb5ac7e7e6bfc66968b89f01f512a"

GITHUB_BASE_URI = "https://github.com/jqlang/${BPN}/releases/"
SRC_URI = "${GITHUB_BASE_URI}/download/${BPN}-${PV}/${BPN}-${PV}.tar.gz \
    file://run-ptest \
    "
SRC_URI[sha256sum] = "91811577f91d9a6195ff50c2bffec9b72c8429dc05ec3ea022fd95c06d2b319c"

inherit autotools github-releases ptest

UPSTREAM_CHECK_REGEX = "releases/tag/${BPN}-(?P<pver>\d+(\.\d+)+)"

PACKAGECONFIG ?= "oniguruma"

PACKAGECONFIG[docs] = "--enable-docs,--disable-docs,ruby-native"
PACKAGECONFIG[maintainer-mode] = "--enable-maintainer-mode,--disable-maintainer-mode,flex-native bison-native"
PACKAGECONFIG[oniguruma] = "--with-oniguruma,--without-oniguruma,onig"
# enable if you want ptest running under valgrind
PACKAGECONFIG[valgrind] = "--enable-valgrind,--disable-valgrind,valgrind"

do_configure:append() {
	sed -i -e "/^ac_cs_config=/ s:${WORKDIR}::g" ${B}/config.status
}

do_install_ptest() {
    cp -rf ${S}/tests ${D}${PTEST_PATH}
    cp -rf ${B}/.libs ${D}${PTEST_PATH}
    # libjq.so.* is packaged in the main jq component, so remove it from ptest
    rm -f ${D}${PTEST_PATH}/.libs/libjq.so.*
    ln -sf ${bindir}/jq ${D}${PTEST_PATH}
    if [ "${@bb.utils.contains('PACKAGECONFIG', 'valgrind', 'true', 'false', d)}" = "false" ]; then
        sed -i 's:#export NO_VALGRIND=1:export NO_VALGRIND=1:g' ${D}${PTEST_PATH}/run-ptest
    fi
    # handle multilib
    sed -i s:@libdir@:${libdir}:g ${D}${PTEST_PATH}/run-ptest
}

BBCLASSEXTEND = "native"
