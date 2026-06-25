inherit cross-canadian

SUMMARY = "crash utility (cross-canadian crash for ${TARGET_ARCH} target)"
PN = "crash-cross-canadian-${TRANSLATED_TARGET_ARCH}"
BPN = "crash"

require crash.inc

DEPENDS:append = " \
    nativesdk-ncurses \
    nativesdk-expat \
    nativesdk-gettext \
    nativesdk-gmp \
    nativesdk-mpfr \
    nativesdk-readline \
    nativesdk-zlib \
    virtual/nativesdk-cross-cc \
    virtual/nativesdk-cross-binutils \
    virtual/nativesdk-compilerlibs \
    virtual/nativesdk-libc \
"

RDEPENDS:${PN} = "nativesdk-liblzma"

EXTRA_OEMAKE:class-cross-canadian = ' \
    RPMPKG="${PV}" \
    CROSS_COMPILE="${HOST_PREFIX}" \
    HOSTCC="gcc" \
    CFLAGS="${CFLAGS} -fcommon --sysroot=${STAGING_DIR_HOST}" \
    CXXFLAGS="${CXXFLAGS} -fcommon --sysroot=${STAGING_DIR_HOST}" \
    LDFLAGS="${LDFLAGS} --sysroot=${STAGING_DIR_HOST}" \
    GDB_TARGET="${BUILD_SYS} --target=${TARGET_SYS}" \
    GDB_HOST="${HOST_SYS}" \
    GDB_MAKE_JOBS="${PARALLEL_MAKE}" \
    GDB_CONF_FLAGS="--host=${HOST_SYS} \
                    --build=${BUILD_SYS} \
                    --target=${TARGET_SYS} \
                    --disable-gdbserver \
                    --disable-gprofng \
                    --with-sysroot=/ \
                    ac_cv_type_gregset_t=yes \
                    ac_cv_type_fpregset_t=yes \
                    ac_cv_header_sys_procfs_h=yes" \
'

# Force the SDK cross-compiler during the command execution phase
do_compile() {
    oe_runmake ${EXTRA_OEMAKE} CC="${HOST_PREFIX}gcc ${HOST_CC_ARCH}" CXX="${HOST_PREFIX}g++ ${HOST_CC_ARCH}" RECIPE_SYSROOT=${RECIPE_SYSROOT}
}

# To ship crash into your sdk, you should create/update a packagegroup-cross-canadian.bbappend and
# add the following
# CRASH = "crash-cross-canadian-${TRANSLATED_TARGET_ARCH}"
# RDEPENDS:${PN} += "${@all_multilib_tune_values(d, 'CRASH')}"
#
# You should also add some kernel packages in your sdk, add the followng in your conf/local.conf:
#
# TOOLCHAIN_TARGET_TASK += "\
#     kernel-vmlinux \
#     kernel-dbg \
#     kernel-dev \
# "
#
# After sourcing the sdk environment script, you can analyze a kernel panic dump with
#
# crash $OECORE_TARGET_SYSROOT/boot/<vmlinux file> $OECORE_TARGET_SYSROOT/boot/<System.map file> <your vmcore>

do_install:class-cross-canadian () {
    rm -rf ${D}
    install -d ${D}${bindir}
    install -m 0755 ${S}/crash ${D}${bindir}/crash
    cross_canadian_bindirlinks
}
