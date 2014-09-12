SUMMARY = "Embedded Linux Device Driver Tests based on Linux Test Project"
HOMEPAGE = "http://arago-project.org/git/projects/test-automation/ltp-ddt.git"
SECTION = "console/utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

PROVIDES += "ltp"
DEPENDS += "zip-native virtual/kernel alsa-lib"

RDEPENDS_${PN} += "pm-qa"

inherit autotools-brokensep module-base kernel-module-split

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "903f70a11eb77cbad62d7ecbe7dcbaf61be8ff99"
BRANCH ?= "master"

SRC_URI = "git://arago-project.org/git/projects/test-automation/ltp-ddt.git;branch=${BRANCH} \
           file://0001-wdt_test_suite-Make-sure-to-include-generated-header.patch \
          "

S = "${WORKDIR}/git"

LTPROOT = "/opt/ltp"

EXTRA_OEMAKE_append = " \
    prefix=${LTPROOT} \
    CROSS_COMPILE=${HOST_PREFIX} \
    SKIP_IDCHECK=1 \
    KERNEL_PATH=${STAGING_KERNEL_DIR} \
    KERNEL_INC=${STAGING_KERNEL_DIR} \
    KERNEL_USR_INC=${STAGING_INCDIR} \
    ALSA_INCPATH=${STAGING_INCDIR} \
    ALSA_LIBPATH=${STAGING_LIBDIR} \
    PLATFORM=${MACHINE} \
    RANLIB=${RANLIB} \
    DESTDIR=${D} \
    CC='${CC}' \
    KERNEL_CC='${KERNEL_CC}' \
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

do_compile_append () {
    oe_runmake modules
}

do_install() {
    oe_runmake install
    install -d ${D}${datadir}
    install -d ${D}${kmoddir}
    cp -a ${D}${LTPROOT}/share/* ${D}${datadir}
    rm -rf ${D}${LTPROOT}/share/
    mv ${D}${LTPROOT}/testcases/bin/ddt/*.ko ${D}${kmoddir}
}

# do_make_scripts should be a separate task for the lock to work
addtask make_scripts before do_compile
do_make_scripts[lockfiles] = "${TMPDIR}/kernel-scripts.lock"
do_make_scripts[deptask] = "do_populate_sysroot"
