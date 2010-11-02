require gcc-common.inc
LICENSE = "GPLv3"

DEPENDS =+ "mpfr gmp libmpc elfutils"
NATIVEDEPS = "mpfr-native gmp-native gettext-native libmpc-native elfutils-native"

SRCREV = "165931"
PV = "4.5"
# BINV should be incremented after updating to a revision
# after a minor gcc release (e.g. 4.5.1 or 4.5.2) has been made
# the value will be minor-release+1 e.g. if minor release was
# 4.5.1 then the value below will be 2 which will mean 4.5.2
# which will be next minor release and so on.

BINV = "${PV}.2"
BRANCH = "gcc-4_5-branch"
PR_append = "+svnr${SRCPV}"
	
SRC_URI = "svn://gcc.gnu.org/svn/gcc/branches;module=${BRANCH} \
	   file://gcc-4.3.1-ARCH_FLAGS_FOR_TARGET.patch \
	   file://100-uclibc-conf.patch \
	   file://gcc-uclibc-locale-ctype_touplow_t.patch \
	   file://cache-amnesia.patch \
	   file://gcc-flags-for-build.patch \
	   file://libstdc++-emit-__cxa_end_cleanup-in-text.patch \
	   file://arm-bswapsi2.patch \
	   file://Makefile.in.patch \
	   file://linaro/gcc-4.5-linaro-r99297.patch \
	   file://linaro/gcc-4.5-linaro-r99298.patch \
	   file://linaro/gcc-4.5-linaro-r99299.patch \
	   file://linaro/gcc-4.5-linaro-r99300.patch \
	   file://linaro/gcc-4.5-linaro-r99301.patch \
	   file://linaro/gcc-4.5-linaro-r99302.patch \
	   file://linaro/gcc-4.5-linaro-r99303.patch \
	   file://linaro/gcc-4.5-linaro-r99304.patch \
	   file://linaro/gcc-4.5-linaro-r99305.patch \
	   file://linaro/gcc-4.5-linaro-r99306.patch \
	   file://linaro/gcc-4.5-linaro-r99307.patch \
	   file://linaro/gcc-4.5-linaro-r99308.patch \
	   file://linaro/gcc-4.5-linaro-r99310.patch \
	   file://linaro/gcc-4.5-linaro-r99312.patch \
	   file://linaro/gcc-4.5-linaro-r99313.patch \
	   file://linaro/gcc-4.5-linaro-r99314.patch \
	   file://linaro/gcc-4.5-linaro-r99315.patch \
	   file://linaro/gcc-4.5-linaro-r99316.patch \
	   file://linaro/gcc-4.5-linaro-r99318.patch \
	   file://linaro/gcc-4.5-linaro-r99319.patch \
	   file://linaro/gcc-4.5-linaro-r99320.patch \
	   file://linaro/gcc-4.5-linaro-r99321.patch \
	   file://linaro/gcc-4.5-linaro-r99322.patch \
	   file://linaro/gcc-4.5-linaro-r99323.patch \
	   file://linaro/gcc-4.5-linaro-r99324.patch \
	   file://linaro/gcc-4.5-linaro-r99325.patch \
	   file://linaro/gcc-4.5-linaro-r99326.patch \
	   file://linaro/gcc-4.5-linaro-r99327.patch \
	   file://linaro/gcc-4.5-linaro-r99332.patch \
	   file://linaro/gcc-4.5-linaro-r99335.patch \
	   file://linaro/gcc-4.5-linaro-r99336.patch \
	   file://linaro/gcc-4.5-linaro-r99337.patch \
	   file://linaro/gcc-4.5-linaro-r99338.patch \
	   file://linaro/gcc-4.5-linaro-r99339.patch \
	   file://linaro/gcc-4.5-linaro-r99340.patch \
	   file://linaro/gcc-4.5-linaro-r99341.patch \
	   file://linaro/gcc-4.5-linaro-r99342.patch \
	   file://linaro/gcc-4.5-linaro-r99343.patch \
	   file://linaro/gcc-4.5-linaro-r99344.patch \
	   file://linaro/gcc-4.5-linaro-r99345.patch \
	   file://linaro/gcc-4.5-linaro-r99346.patch \
	   file://linaro/gcc-4.5-linaro-r99347.patch \
	   file://linaro/gcc-4.5-linaro-r99348.patch \
	   file://linaro/gcc-4.5-linaro-r99349.patch \
#	   file://linaro/gcc-4.5-linaro-r99350.patch \
	   file://linaro/gcc-4.5-linaro-r99351.patch \
	   file://linaro/gcc-4.5-linaro-r99352.patch \
	   file://linaro/gcc-4.5-linaro-r99353.patch \
	   file://linaro/gcc-4.5-linaro-r99354.patch \
	   file://linaro/gcc-4.5-linaro-r99355.patch \
	   file://linaro/gcc-4.5-linaro-r99356.patch \
	   file://linaro/gcc-4.5-linaro-r99357.patch \
	   file://linaro/gcc-4.5-linaro-r99358.patch \
	   file://linaro/gcc-4.5-linaro-r99359.patch \
	   file://linaro/gcc-4.5-linaro-r99360.patch \
	   file://linaro/gcc-4.5-linaro-r99361.patch \
	   file://linaro/gcc-4.5-linaro-r99363.patch \
	   file://linaro/gcc-4.5-linaro-r99364.patch \
	   file://linaro/gcc-4.5-linaro-r99365.patch \
	   file://linaro/gcc-4.5-linaro-r99366.patch \
	   file://linaro/gcc-4.5-linaro-r99367.patch \
	   file://linaro/gcc-4.5-linaro-r99368.patch \
	   file://linaro/gcc-4.5-linaro-r99369.patch \
	   file://linaro/gcc-4.5-linaro-r99371.patch \
	   file://linaro/gcc-4.5-linaro-r99372.patch \
	   file://linaro/gcc-4.5-linaro-r99373.patch \
	   file://linaro/gcc-4.5-linaro-r99374.patch \
	   file://linaro/gcc-4.5-linaro-r99375.patch \
	   file://linaro/gcc-4.5-linaro-r99376.patch \
	   file://linaro/gcc-4.5-linaro-r99377.patch \
	   file://linaro/gcc-4.5-linaro-r99378.patch \
	   file://linaro/gcc-4.5-linaro-r99379.patch \
	   file://linaro/gcc-4.5-linaro-r99380.patch \
	   file://linaro/gcc-4.5-linaro-r99381.patch \
	   file://linaro/gcc-4.5-linaro-r99383.patch \
	   file://linaro/gcc-4.5-linaro-r99384.patch \
	   file://linaro/gcc-4.5-linaro-r99385.patch \
	   file://linaro/gcc-4.5-linaro-r99388.patch \
	   file://linaro/gcc-4.5-linaro-r99391.patch \
	   file://linaro/gcc-4.5-linaro-r99392.patch \
	   file://linaro/gcc-4.5-linaro-r99393.patch \
	   file://linaro/gcc-4.5-linaro-r99395.patch \
	   file://linaro/gcc-4.5-linaro-r99396.patch \
	   file://linaro/gcc-4.5-linaro-r99397.patch \
	   file://linaro/gcc-4.5-linaro-r99398.patch \
	   file://linaro/gcc-4.5-linaro-r99402.patch \
	   file://linaro/gcc-4.5-linaro-r99403.patch \
	   file://linaro/gcc-4.5-linaro-r99404.patch \
	   file://linaro/gcc-4.5-linaro-r99405.patch \
	   file://linaro/gcc-4.5-linaro-r99406.patch \
	   file://linaro/gcc-4.5-linaro-r99407.patch \
	   file://linaro/gcc-4.5-linaro-r99408.patch \
	   file://linaro/gcc-4.5-linaro-r99409.patch \
	   file://linaro/gcc-4.5-linaro-r99410.patch \
	   file://linaro/gcc-4.5-linaro-r99411.patch \
	   file://linaro/gcc-4.5-linaro-r99412.patch \
	   file://linaro/gcc-4.5-linaro-r99413.patch \
	   file://linaro/gcc-4.5-linaro-r99414.patch \
	   file://linaro/gcc-4.5-linaro-r99415.patch \
	   file://gcc-vmovl-PR45805.patch \
	   file://gcc-scalar-widening-pr45847.patch \
	   file://gcc-linaro-fix-lp-653316.patch \
	  "

SRC_URI_append_sh3  = " file://sh3-installfix-fixheaders.patch;patch=1 "

# Language Overrides
FORTRAN = ""
JAVA = ""

rename_srcdir (){
        mv ${WORKDIR}/${BRANCH} ${WORKDIR}/gcc-${PV}
}

do_unpack_append() {
        bb.build.exec_func('rename_srcdir', d)
}

#EXTRA_OECONF_BASE = "  --enable-cheaders=c_std \
#			--enable-libssp \
#			--disable-bootstrap \
#			--disable-libgomp \
#			--disable-libmudflap"
EXTRA_OECONF_BASE = " --enable-lto \
			--enable-libssp \
			--disable-bootstrap \
			--disable-libgomp \
			--disable-libmudflap \
			--enable-cheaders=c_global "

EXTRA_OECONF_INITIAL = "--disable-libmudflap \
			--disable-libgomp \
			--disable-libssp \
			--enable-decimal-float=no"

EXTRA_OECONF_INTERMEDIATE = "--disable-libmudflap \
				--disable-libgomp \
				--disable-libssp"

EXTRA_OECONF_append_libc-uclibc = " --disable-decimal-float "
