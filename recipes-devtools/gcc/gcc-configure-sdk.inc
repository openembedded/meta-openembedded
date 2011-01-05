require gcc-configure-common.inc

# The two lines below conflict, this needs fixing - RP
USE_NLS = '${@base_conditional( "TARGET_OS", "linux-uclibc", "no", "", d )}'
USE_NLS = '${@base_conditional( "TARGET_OS", "linux-uclibcgnueabi", "no", "", d )}'

EXTRA_OECONF_PATHS = "--with-local-prefix=${SDKPATH}/sysroots/${TARGET_SYS}${target_exec_prefix} \
                      --with-gxx-include-dir=${target_includedir}/c++ \
                      --with-build-time-tools=${STAGING_DIR_NATIVE}${prefix_native}/${TARGET_SYS}/bin \
                      --with-sysroot=${SDKPATH}/sysroots/${TARGET_SYS} \
                      --with-build-sysroot=${STAGING_DIR_TARGET}"

#
# gcc-cross looks and finds these in ${exec_prefix} but we're not so lucky
# for the sdk. Hardcoding the paths ensures the build doesn't go canadian or worse.
#
export AR_FOR_TARGET = "${TARGET_PREFIX}ar"
export AS_FOR_TARGET = "${TARGET_PREFIX}as"
export DLLTOOL_FOR_TARGET = "${TARGET_PREFIX}dlltool"
export CC_FOR_TARGET = "${TARGET_PREFIX}gcc"
export CXX_FOR_TARGET = "${TARGET_PREFIX}g++"
export LD_FOR_TARGET = "${TARGET_PREFIX}ld"
export LIPO_FOR_TARGET = "${TARGET_PREFIX}lipo"
export NM_FOR_TARGET = "${TARGET_PREFIX}nm"
export OBJDUMP_FOR_TARGET = "${TARGET_PREFIX}objdump"
export RANLIB_FOR_TARGET = "${TARGET_PREFIX}ranlib"
export STRIP_FOR_TARGET = "${TARGET_PREFIX}strip"
export WINDRES_FOR_TARGET = "${TARGET_PREFIX}windres"

#
# We need to override this and make sure the compiler can find staging
#
export ARCH_FLAGS_FOR_TARGET = "--sysroot=${STAGING_DIR_TARGET}"

do_configure () {
	export CC_FOR_BUILD="${BUILD_CC}"
	export CXX_FOR_BUILD="${BUILD_CXX}"
	export CFLAGS_FOR_BUILD="${BUILD_CFLAGS}"
	export CPPFLAGS_FOR_BUILD="${BUILD_CPPFLAGS}"
	export CXXFLAGS_FOR_BUILD="${BUILD_CXXFLAGS}"
	export LDFLAGS_FOR_BUILD="${BUILD_LDFLAGS}"
	(cd ${S} && gnu-configize) || die "failure running gnu-configize"
	oe_runconf
}

do_compile () {
	oe_runmake all-host all-target-libgcc
}
