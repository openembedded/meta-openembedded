#
# Build the list of lanaguages to build.
#
# These can be overridden by the version specific .inc file.

# Java (gcj doesn't work on all architectures)
JAVA ?= ",java"
JAVA_arm ?= ""
JAVA_armeb ?= ""
JAVA_mipsel ?= ""
JAVA_sh3 ?= ""
# gcc 3.x expects 'f77', 4.0 expects 'f95', 4.1 and 4.2 expect 'fortran'
FORTRAN ?= ",f77"
LANGUAGES ?= "c,c++${FORTRAN}${JAVA}"
# disable --enable-target-optspace for powerpc SPE
# at -Os libgcc.so.1 creates references into
# hidden symbols in libgcc.a which linker complains
# when linking shared libraries further in the build like (gnutls)

SPECIAL_ARCH_LIST = "powerpc"
OPTSPACE = ${@base_contains("SPECIAL_ARCH_LIST", "${TARGET_ARCH}", "", "--enable-target-optspace",d)}

EXTRA_OECONF_BASE ?= ""
EXTRA_OECONF_PATHS ?= ""
EXTRA_OECONF_INITIAL ?= ""
EXTRA_OECONF_INTERMEDIATE ?= ""

GCCMULTILIB = "--disable-multilib"

EXTRA_OECONF = "${@['--enable-clocale=generic', ''][bb.data.getVar('USE_NLS', d, 1) != 'no']} \
                --with-gnu-ld \
                --enable-shared \
                --enable-languages=${LANGUAGES} \
                --enable-threads=posix \
                ${GCCMULTILIB} \
                --enable-c99 \
                --enable-long-long \
                --enable-symvers=gnu \
                --enable-libstdcxx-pch \
                --program-prefix=${TARGET_PREFIX} \
                ${OPTSPACE} \
                ${EXTRA_OECONF_BASE} \
                ${EXTRA_OECONF_FPU} \
                ${EXTRA_OECONF_PATHS} \
                ${@get_gcc_mips_plt_setting(bb, d)}"

# Build uclibc compilers without cxa_atexit support
EXTRA_OECONF_append_linux               = " --enable-__cxa_atexit"
EXTRA_OECONF_append_linux-gnueabi       = " --enable-__cxa_atexit"
EXTRA_OECONF_append_linux-uclibc        = " --disable-__cxa_atexit"
EXTRA_OECONF_append_linux-uclibcgnueabi = " --disable-__cxa_atexit"
EXTRA_OECONF_FPU = "${@get_gcc_fpu_setting(bb, d)}"
CPPFLAGS = ""

# Used by configure to define additional values for FLAGS_FOR_TARGET -
# passed to all the compilers.
ARCH_FLAGS_FOR_TARGET = "${TARGET_CC_ARCH}"
EXTRA_OEMAKE += "ARCH_FLAGS_FOR_TARGET='${ARCH_FLAGS_FOR_TARGET}'"

SYSTEMHEADERS = "${target_includedir}"
SYSTEMLIBS = "${target_base_libdir}/"
SYSTEMLIBS1 = "${target_libdir}/"

do_configure () {
	# Setup these vars for cross building only
	# ... because foo_FOR_TARGET apparently gets misinterpreted inside the
	# gcc build stuff when the build is producing a cross compiler - i.e.
	# when the 'current' target is the 'host' system, and the host is not
	# the target (because the build is actually making a cross compiler!)
	if [ "${BUILD_SYS}" != "${HOST_SYS}" ]; then
		export CC_FOR_TARGET="${CC}"
		export GCC_FOR_TARGET="${CC}"
		export CXX_FOR_TARGET="${CXX}"
		export AS_FOR_TARGET="${HOST_PREFIX}as"
		export LD_FOR_TARGET="${HOST_PREFIX}ld"
		export NM_FOR_TARGET="${HOST_PREFIX}nm"
		export AR_FOR_TARGET="${HOST_PREFIX}ar"
		export GFORTRAN_FOR_TARGET="gfortran"
		export RANLIB_FOR_TARGET="${HOST_PREFIX}ranlib"
	fi
	export CC_FOR_BUILD="${BUILD_CC}"
	export CXX_FOR_BUILD="${BUILD_CXX}"
	export CFLAGS_FOR_BUILD="${BUILD_CFLAGS}"
	export CPPFLAGS_FOR_BUILD="${BUILD_CPPFLAGS}"
	export CXXFLAGS_FOR_BUILD="${BUILD_CXXFLAGS}"
	export LDFLAGS_FOR_BUILD="${BUILD_LDFLAGS}"
	export ARCH_FLAGS_FOR_TARGET="${ARCH_FLAGS_FOR_TARGET}"
	(cd ${S} && gnu-configize) || die "failure running gnu-configize"
	
	# teach gcc to find correct target includedir when checking libc ssp support
	sed -i 's:^\([ 	]*\)glibc_header_dir=\"${with_build_sysroot}/usr/include\":\1glibc_header_dir=\"${with_build_sysroot}${SYSTEMHEADERS}\":g' ${S}/gcc/configure.ac
	sed -i 's:^\([ 	]*\)glibc_header_dir=\"${with_build_sysroot}/usr/include\":\1glibc_header_dir=\"${with_build_sysroot}${SYSTEMHEADERS}\":g' ${S}/gcc/configure

	# splice our idea of where the headers live into gcc's world
	echo "NATIVE_SYSTEM_HEADER_DIR = ${SYSTEMHEADERS}" > ${T}/t-oe
	sed 's%^tmake_file=.*$%& ${T}/t-oe%' < ${S}/gcc/Makefile.in >${S}/gcc/Makefile.in.new
	mv ${S}/gcc/Makefile.in.new ${S}/gcc/Makefile.in
	cat ${S}/gcc/defaults.h | grep -v "\#endif.*GCC_DEFAULTS_H" > ${S}/gcc/defaults.h.new
	echo "#ifndef STANDARD_INCLUDE_DIR" >> ${S}/gcc/defaults.h.new
	echo "#define STANDARD_INCLUDE_DIR \"${SYSTEMHEADERS}\"" >> ${S}/gcc/defaults.h.new
	echo "#endif" >> ${S}/gcc/defaults.h.new
	echo "#ifndef STANDARD_STARTFILE_PREFIX_1" >> ${S}/gcc/defaults.h.new
	echo "#define STANDARD_STARTFILE_PREFIX_1 \"${SYSTEMLIBS}\"" >> ${S}/gcc/defaults.h.new
	echo "#endif" >> ${S}/gcc/defaults.h.new
	echo "#ifndef STANDARD_STARTFILE_PREFIX_2" >> ${S}/gcc/defaults.h.new
	echo "#define STANDARD_STARTFILE_PREFIX_2 \"${SYSTEMLIBS1}\"" >> ${S}/gcc/defaults.h.new
	echo "#endif" >> ${S}/gcc/defaults.h.new
	echo "#endif /* ! GCC_DEFAULTS_H */" >> ${S}/gcc/defaults.h.new
	mv ${S}/gcc/defaults.h.new ${S}/gcc/defaults.h
	oe_runconf
}

