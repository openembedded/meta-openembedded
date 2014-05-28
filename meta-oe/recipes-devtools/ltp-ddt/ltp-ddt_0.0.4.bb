SUMMARY = "Embedded Linux Device Driver Tests based on Linux Test Project"
HOMEPAGE = "http://arago-project.org/git/projects/test-automation/ltp-ddt.git"
SECTION = "console/utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"
PR = "r1"

PROVIDES += "ltp"
DEPENDS += "zip-native virtual/kernel alsa-lib"

inherit autotools module-base kernel-module-split

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "4f77e2e33357d2b23211ecd22f27f521aa01469a"
BRANCH ?= "master"

SRC_URI = "git://arago-project.org/git/projects/test-automation/ltp-ddt.git;branch=${BRANCH} \
           file://0001-wdt_test_suite-Make-sure-to-include-generated-header.patch \
           file://0001-KERNEL_INC-in-modern-kernel-should-point-at-toplevel.patch \
          "

S = "${WORKDIR}/git"

LTPROOT = "/opt/ltp"

EXTRA_OEMAKE_append = " \
    prefix=${LTPROOT} \
    CROSS_COMPILE=${HOST_PREFIX} \
    SKIP_IDCHECK=1 \
    KERNEL_INC=${STAGING_KERNEL_DIR} \
    KERNEL_USR_INC=${STAGING_INCDIR} \
    ALSA_INCPATH=${STAGING_INCDIR} \
    ALSA_LIBPATH=${STAGING_LIBDIR} \
    PLATFORM=${MACHINE} \
    RANLIB=${RANLIB} \
"

TARGET_CC_ARCH += "${LDFLAGS}"

FILES_${PN}-dbg += " \
    ${LTPROOT}/.debug \
    ${LTPROOT}/bin/.debug \
    ${LTPROOT}/runtest/.debug \
    ${LTPROOT}/testcases/bin/.debug \
    ${LTPROOT}/testcases/bin/*/bin/.debug \
    ${LTPROOT}/testcases/bin/*/test/.debug \
    ${LTPROOT}/testcases/bin/ddt/.debug \
    ${LTPROOT}/testcases/bin/ddt/*/bin/.debug \
    ${LTPROOT}/testcases/bin/ddt/*/test/.debug \
    ${LTPROOT}/testcases/realtime/*/*/.debug \
"

FILES_${PN}-staticdev += "${LTPROOT}/lib"
FILES_${PN} += "${LTPROOT}/*"

do_configure() {
    cp ${S}/include/config.h.default ${S}/include/config.h
    cp ${S}/include/mk/config.mk.default ${S}/include/mk/config.mk
    cp ${S}/include/mk/features.mk.default ${S}/include/mk/features.mk
    echo "${TAG}" > ${S}/ChangeLog
}

kmoddir = "/lib/modules/${KERNEL_VERSION}/kernel/drivers/ddt"

addtask make_scripts after do_patch before do_compile
do_make_scripts[lockfiles] = "${TMPDIR}/kernel-scripts.lock"
do_make_scripts[deptask] = "do_populate_sysroot"

do_compile_append () {
    oe_runmake DESTDIR=${D} modules
}

do_install() {
    oe_runmake DESTDIR=${D} install
    install -d ${D}${datadir}
    install -d ${D}${kmoddir}
    cp -a ${D}${LTPROOT}/share/* ${D}${datadir}
    rm -rf ${D}${LTPROOT}/share/
    mv ${D}${LTPROOT}/testcases/bin/ddt/*.ko ${D}${kmoddir}
}
