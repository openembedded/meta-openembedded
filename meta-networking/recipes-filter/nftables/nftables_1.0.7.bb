SUMMARY = "Netfilter Tables userspace utillites"
SECTION = "net"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=81ec33bb3e47b460fc993ac768c74b62"

DEPENDS = "libmnl libnftnl bison-native \
           ${@bb.utils.contains('PACKAGECONFIG', 'mini-gmp', '', 'gmp', d)}"

SRC_URI = "http://www.netfilter.org/projects/nftables/files/${BP}.tar.xz \
           file://run-ptest \
          "
SRC_URI[sha256sum] = "c12ac941fff9adaedf17367d5ce213789b98a0d314277bc22b3d71e10891f412"

inherit autotools manpages pkgconfig ptest

PACKAGECONFIG ?= "python readline json"
PACKAGECONFIG[editline] = "--with-cli=editline, , libedit, , , linenoise readline"
PACKAGECONFIG[json] = "--with-json, --without-json, jansson"
PACKAGECONFIG[linenoise] = "--with-cli=linenoise, , linenoise, , , editline readline"
PACKAGECONFIG[manpages] = "--enable-man-doc, --disable-man-doc, asciidoc-native"
PACKAGECONFIG[mini-gmp] = "--with-mini-gmp, --without-mini-gmp"
PACKAGECONFIG[python] = ",, python3-setuptools-native"
PACKAGECONFIG[readline] = "--with-cli=readline, , readline, , , editline linenoise"
PACKAGECONFIG[xtables] = "--with-xtables, --without-xtables, iptables"

# Disable the python via autoconf so we can build it separately via setuptools3
EXTRA_OECONF = "--disable-python \
    ${@bb.utils.contains_any('PACKAGECONFIG', 'editline linenoise readline', '', '--without-cli', d)}"

SETUPTOOLS_SETUP_PATH = "${S}/py"

inherit ${@bb.utils.contains('PACKAGECONFIG', 'python', 'setuptools3', '', d)}

RRECOMMENDS:${PN} += "kernel-module-nf-tables"

PACKAGES =+ "${PN}-python"
FILES:${PN}-python = "${PYTHON_SITEPACKAGES_DIR}"
RDEPENDS:${PN}-python = "python3-core python3-json ${PN}"

# Explicitly define do_configure, do_compile and do_install because both autotools and setuptools3
# have EXPORT_FUNCTIONS do_configure do_compile do_install
do_configure() {
    autotools_do_configure
    if ${@bb.utils.contains('PACKAGECONFIG', 'python', 'true', 'false', d)}; then
        setuptools3_do_configure
    fi
}

do_compile() {
    autotools_do_compile
    if ${@bb.utils.contains('PACKAGECONFIG', 'python', 'true', 'false', d)}; then
        setuptools3_do_compile
    fi
}

do_install() {
    autotools_do_install
    if ${@bb.utils.contains('PACKAGECONFIG', 'python', 'true', 'false', d)}; then
        setuptools3_do_install
    fi
}

RDEPENDS:${PN}-ptest += " ${PN}-python bash make iproute2 iputils-ping procps python3-core python3-ctypes python3-json python3-misc sed util-linux"

TESTDIR = "tests"

PRIVATE_LIBS:${PN}-ptest:append = " libnftables.so.1"

do_install_ptest() {
    cp -rf ${S}/build-aux ${D}${PTEST_PATH}
    cp -rf ${S}/src ${D}${PTEST_PATH}
    mkdir -p ${D}${PTEST_PATH}/src/.libs
    cp -rf ${B}/src/.libs/* ${D}${PTEST_PATH}/src/.libs
    cp -rf ${B}/src/.libs/nft ${D}${PTEST_PATH}/src/
    cp -rf ${S}/${TESTDIR} ${D}${PTEST_PATH}/${TESTDIR}
    sed -i 's#/usr/bin/python#/usr/bin/python3#' ${D}${PTEST_PATH}/${TESTDIR}/json_echo/run-test.py
    sed -i 's#/usr/bin/env python#/usr/bin/env python3#' ${D}${PTEST_PATH}/${TESTDIR}/py/nft-test.py
    # handle multilib
    sed -i s:@libdir@:${libdir}:g ${D}${PTEST_PATH}/run-ptest
}
