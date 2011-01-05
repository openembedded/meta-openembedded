require gcc-configure-common.inc

USE_NLS = '${@base_conditional( "TARGET_OS", "linux-uclibc", "no", "", d )}'

EXTRA_OECONF += " --enable-poison-system-directories "

EXTRA_OECONF_PATHS = "--with-local-prefix=${STAGING_DIR_TARGET}${target_exec_prefix} \
		      --with-gxx-include-dir=${target_includedir}/c++ \
                      --with-sysroot=${STAGING_DIR_TARGET} \
                      --with-build-sysroot=${STAGING_DIR_TARGET}"

do_compile_prepend () {
	export CC="${BUILD_CC}"
	export AR_FOR_TARGET="${TARGET_SYS}-ar"
	export RANLIB_FOR_TARGET="${TARGET_SYS}-ranlib"
	export LD_FOR_TARGET="${TARGET_SYS}-ld"
	export NM_FOR_TARGET="${TARGET_SYS}-nm"
	export CC_FOR_TARGET="${CCACHE} ${TARGET_SYS}-gcc ${TARGET_CC_ARCH}"
}

LIBGCCS_VAR = "-lgcc_s"
LIBGCCS_VAR_avr32 = ""

do_package_write_ipk[depends] += "virtual/libc:do_package"
