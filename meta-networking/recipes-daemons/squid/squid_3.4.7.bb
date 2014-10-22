SUMMARY = "A fully-featured http proxy and web-cache daemon for Linux"
DESCRIPTION = "A fully-featured http proxy and web-cache daemon for Linux. \
Squid offers a rich access control, authorization and logging environment to \
develop web proxy and content serving applications. \
Squid offers a rich set of traffic optimization options, most of which are \
enabled by default for simpler installation and high performance. \
"
HOMEPAGE = "http://www.squid-cache.org"
SECTION = "network"
LICENSE = "GPLv2+"

MAJ_VER = "${@oe.utils.trim_version("${PV}", 1)}"
MIN_VER = "${@oe.utils.trim_version("${PV}", 2)}"

SRC_URI = "http://www.squid-cache.org/Versions/v${MAJ_VER}/${MIN_VER}/${BPN}-${PV}.tar.bz2 \
           file://Set-up-for-cross-compilation.patch \
           file://Skip-AC_RUN_IFELSE-tests.patch \
           file://Fix-flawed-dynamic-ldb-link-test-in-configure.patch \
           file://Change-ksh-reference-in-krb-ldap-helper-to-bash.patch \
           file://squid-use-serial-tests-config-needed-by-ptest.patch \
           file://run-ptest \
           file://volatiles.03_squid \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=c492e2d6d32ec5c1aad0e0609a141ce9 \
                    file://COPYRIGHT;md5=2900f50789c498be8e9f1eb23b55cbe9 \
                   "
SRC_URI[md5sum] = "9951034b10f7ee0f45a95cfae61c57c2"
SRC_URI[sha256sum] = "7b423f3d3495a317503ca559ea535f80445fd7c4e3c3e268cb7a8c97c61af2b6"

DEPENDS = "libtool krb5 openldap db cyrus-sasl"

inherit autotools useradd ptest

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system --no-create-home --home-dir /var/run/squid --shell /bin/false --user-group squid"

PACKAGECONFIG ??= ""
PACKAGECONFIG[libnetfilter-conntrack] = "--with-netfilter-conntrack=${includedir}, --without-netfilter-conntrack, libnetfilter-conntrack"
EXTRA_OECONF += "--with-default-user=squid"

TESTDIR = "test-suite"
do_compile_ptest() {
    oe_runmake -C ${TESTDIR} buildtest-TESTS
}

do_install_ptest() {
    cp -rf ${B}/${TESTDIR} ${D}${PTEST_PATH}
    cp -rf ${S}/${TESTDIR} ${D}${PTEST_PATH}

    # do NOT need to rebuild Makefile itself
    sed -i 's/^Makefile:.*$/Makefile:/' ${D}${PTEST_PATH}/${TESTDIR}/Makefile
}

do_install_append() {
	install -d ${D}${sysconfdir}/default/volatiles
	install -m 0644 ${WORKDIR}/volatiles.03_squid  ${D}${sysconfdir}/default/volatiles/volatiles.03_squid
	rmdir "${D}${localstatedir}/run/${BPN}"
	rmdir --ignore-fail-on-non-empty "${D}${localstatedir}/run"
}

FILES_${PN} += "${libdir} ${datadir}/errors ${datadir}/icons"
FILES_${PN}-dbg += "/usr/src/debug"
FILES_${PN}-doc += "${datadir}/*.txt"

RDEPENDS_${PN}-ptest += "make"
