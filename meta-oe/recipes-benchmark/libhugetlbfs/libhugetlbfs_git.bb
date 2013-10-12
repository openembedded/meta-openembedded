DESCRIPTION = "A library which provides easy access to huge pages of memory"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LGPL-2.1;md5=2d5025d4aa3495befef8f17206a5b0a1"

DEPENDS = "sysfsutils perl"
RDEPENDS_${PN} += "python python-io python-lang python-subprocess python-resource"

SRCREV = "49fedbe172343b3f7b39dc81bd2d81a18a34eb2f"
SRC_URI = "git://git.code.sf.net/p/libhugetlbfs/code \
    file://aarch64-support.patch \
    file://aarch64-unit-test-fixes.patch \
    file://add-PROT-NONE-to-the-mprotest-test.patch \
    file://skip-checking-LIB32-and-LIB64-if-they-point-to-the-s.patch \
    file://libhugetlbfs-avoid-search-host-library-path-for-cros.patch \
    file://fix-lib64-can-not-be-shiped-in-64bit-target.patch \
    file://tests-Makefile-install-static-4G-edge-testcases.patch \
    file://0001-run_test.py-not-use-hard-coded-path-.-obj-hugeadm.patch \
    file://0001-install-perl-lib-to-directory-perl-instead-of-perl5.patch \
"

S = "${WORKDIR}/git"

COMPATIBLE_HOST = "(x86_64|powerpc|powerpc64|aarch64).*-linux*"


EXTRA_OEMAKE = "'ARCH=${TARGET_ARCH}' 'OPT=${CFLAGS}' 'CC=${CC}' BUILDTYPE=NATIVEONLY ${LIBARGS}"
CFLAGS += "-fexpensive-optimizations -frename-registers -fomit-frame-pointer -g0"

TARGET_CC_ARCH += "${LDFLAGS}"

#The CUSTOM_LDSCRIPTS doesn't work with the gold linker
do_configure() {
    if [ "${@base_contains('DISTRO_FEATURES', 'ld-is-gold', 'ld-is-gold', '', d)}" = "ld-is-gold" ] ; then
      sed -i 's/CUSTOM_LDSCRIPTS = yes/CUSTOM_LDSCRIPTS = no/'  Makefile
    fi
}

do_install() {
        oe_runmake PREFIX=${prefix} DESTDIR=${D}  \
          INST_TESTSDIR32=/opt/libhugetlbfs/tests \
          INST_TESTSDIR64=/opt/libhugetlbfs/tests \
          install-tests
}

PARALLEL_MAKE_pn-${PN} = ""

PACKAGES =+ "${PN}-perl ${PN}-tests "
FILES_${PN}-dbg += "${libdir}/libhugetlbfs/tests/obj32/.debug ${libdir}/libhugetlbfs/tests/obj64/.debug"
FILES_${PN}-perl = "${libdir}/perl"
FILES_${PN}-tests += "/opt/libhugetlbfs/tests"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
