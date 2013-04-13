require recipes-devtools/gcc/gcc-common.inc

PR = "r27"

# Third digit in PV should be incremented after a minor release
# happens from this branch on gcc e.g. currently its 4.6.0
# when 4.6.1 is releases and we bump SRCREV beyond the release
# on branch then PV should be incremented to 4.6.1+svnr${SRCPV}
# to reflect that change

PV = "4.6.3+svnr${SRCPV}"

# BINV should be incremented after updating to a revision
# after a minor gcc release (e.g. 4.6.1 or 4.6.2) has been made
# the value will be minor-release+1 e.g. if current minor release was
# 4.6.1 then the value below will have 2 which will mean 4.6.2
# which will be next minor release and so on.

BINV = "4.6.4"

SRCREV = "184847"
BRANCH = "gcc-4_6-branch"
FILESPATH = "${@base_set_filespath([ '${FILE_DIRNAME}/gcc-4.6' ], d)}"

DEPENDS =+ "mpfr gmp libmpc"
NATIVEDEPS = "mpfr-native gmp-native libmpc-native zlib-native"

LICENSE="GPL-3.0-with-GCC-exception & GPLv3"

LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552 \
    file://COPYING3;md5=d32239bcb673463ab874e80d47fae504 \
    file://COPYING3.LIB;md5=6a6a8e020838b23406c81b19c1d46df6 \
    file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1 \
    file://COPYING.RUNTIME;md5=fe60d87048567d4fe8c8a0ed2448bcc8"

SRC_URI = "svn://gcc.gnu.org/svn/gcc/branches;module=${BRANCH};protocol=http \
    file://gcc-4.3.1-ARCH_FLAGS_FOR_TARGET.patch \
    file://100-uclibc-conf.patch \
    file://gcc-uclibc-locale-ctype_touplow_t.patch \
    file://cache-amnesia.patch \
    file://gcc-flags-for-build.patch \
    file://103-uclibc-conf-noupstream.patch  \
    file://200-uclibc-locale.patch  \
    file://203-uclibc-locale-no__x.patch; \
    file://204-uclibc-locale-wchar_fix.patch; \
    file://205-uclibc-locale-update.patch; \
    file://301-missing-execinfo_h.patch  \
    file://302-c99-snprintf.patch  \
    file://303-c99-complex-ugly-hack.patch  \
    file://304-index_macro.patch  \
    file://305-libmudflap-susv3-legacy.patch  \
    file://306-libstdc++-namespace.patch  \
    file://740-sh-pr24836.patch  \
    file://800-arm-bigendian.patch  \
    file://904-flatten-switch-stmt-00.patch  \
    file://arm-nolibfloat.patch  \
    file://arm-softfloat.patch \
    file://zecke-xgcc-cpp.patch \
    file://gcc-poison-system-directories.patch \
    file://gcc-poison-dir-extend.patch \
    file://gcc-4.3.3-SYSROOT_CFLAGS_FOR_TARGET.patch \
    file://64bithack.patch \
    file://optional_libstdc.patch \
    file://disable_relax_pic_calls_flag.patch \
    file://COLLECT_GCC_OPTIONS.patch \
    file://use-defaults.h-and-t-oe-in-B.patch \
    file://powerpc-e5500.patch \
    file://fix-for-ice-50099.patch \
    file://gcc-with-linker-hash-style.patch \
    file://pr46934.patch \
    file://pr32219.patch \
    file://pr47551.patch \
    file://gcc-arm-set-cost.patch \
    file://GPLUSPLUS_INCLUDE_DIR_with_sysroot.patch \
    file://fortran-cross-compile-hack.patch \
    file://cpp-honour-sysroot.patch \
    file://mips64-default-n64.patch \
    file://gcc-argument-list-too-long.patch \
"

SRC_URI_append_sh3  = " file://sh3-installfix-fixheaders.patch "

#S = "${WORKDIR}/${BRANCH}"
S = "${TMPDIR}/work-shared/gcc-${PV}-${PR}/${BRANCH}"
B = "${WORKDIR}/${BRANCH}/build.${HOST_SYS}.${TARGET_SYS}"

# Language Overrides
FORTRAN = ""
JAVA = ""

EXTRA_OECONF_BASE = " --enable-lto \
    --enable-libssp \
    --disable-bootstrap \
    --disable-libgomp \
    --disable-libmudflap \
    --with-system-zlib \
    --with-linker-hash-style=${LINKER_HASH_STYLE} \
    --with-ppl=no \
    --with-cloog=no \
    --enable-cheaders=c_global "

EXTRA_OECONF_INITIAL = "--disable-libmudflap \
    --disable-libgomp \
    --disable-libssp \
    --disable-libquadmath \
    --with-system-zlib \
    --disable-lto \
    --disable-plugin \
    --enable-decimal-float=no"

EXTRA_OECONF_INTERMEDIATE = "--disable-libmudflap \
    --disable-libgomp \
    --disable-libquadmath \
    --with-system-zlib \
    --disable-lto \
    --disable-plugin \
    --disable-libssp"

EXTRA_OECONF_append_libc-uclibc = " --disable-decimal-float "
