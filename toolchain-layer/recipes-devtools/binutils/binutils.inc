SUMMARY = "A GNU collection of binary utilities"
DESCRIPTION = "The GNU Binutils are a collection of binary tools. \
The main ones are ld (GNU Linker), and as (GNU Assembler). This \
package also includes addition tools such as addr2line (Converts \
addresses into filenames and line numbers), ar (utility for creating, \
modifying and extracting archives), nm (list symbols in object \
files), objcopy (copy and translate object files), objdump (Display \
object information), and other tools and related libraries."
HOMEPAGE = "http://www.gnu.org/software/binutils/"
BUGTRACKER = "http://sourceware.org/bugzilla/"
SECTION = "devel"
LICENSE = "GPLv3"

DEPENDS = "flex-native bison-native"

inherit autotools gettext

PACKAGES += "${PN}-symlinks"

FILES_${PN} = " \
	${bindir}/${TARGET_PREFIX}* \
	${libdir}/lib*-*.so \
	${prefix}/${TARGET_SYS}/bin/*"

FILES_${PN}-dev = " \
	${includedir} \
	${libdir}/*.a \
	${libdir}/*.la \
	${libdir}/libbfd.so \
	${libdir}/libopcodes.so"

FILES_${PN}-symlinks = " \
	${bindir}/addr2line \
	${bindir}/as \
	${bindir}/c++filt \
	${bindir}/gprof \
	${bindir}/ld \
	${bindir}/nm \
	${bindir}/objcopy \
	${bindir}/objdump \
	${bindir}/ranlib \
	${bindir}/readelf \
	${bindir}/size \
	${bindir}/strip"

B = "${S}/build.${HOST_SYS}.${TARGET_SYS}"

EXTRA_OECONF = "--program-prefix=${TARGET_PREFIX} \
                --enable-install-libbfd \
                --enable-shared"

EXTRA_OECONF_virtclass-native = "--enable-target=all --enable-64-bit-bfd --enable-install-libbfd"

# This is necessary due to a bug in the binutils Makefiles
# EXTRA_OEMAKE = "configure-build-libiberty all"

export AR = "${HOST_PREFIX}ar"
export AS = "${HOST_PREFIX}as"
export LD = "${HOST_PREFIX}ld"
export NM = "${HOST_PREFIX}nm"
export RANLIB = "${HOST_PREFIX}ranlib"
export OBJCOPY = "${HOST_PREFIX}objcopy"
export OBJDUMP = "${HOST_PREFIX}objdump"

export AR_FOR_TARGET = "${TARGET_PREFIX}ar"
export AS_FOR_TARGET = "${TARGET_PREFIX}as"
export LD_FOR_TARGET = "${TARGET_PREFIX}ld"
export NM_FOR_TARGET = "${TARGET_PREFIX}nm"
export RANLIB_FOR_TARGET = "${TARGET_PREFIX}ranlib"

export CC_FOR_HOST = "${CCACHE}${HOST_PREFIX}gcc ${HOST_CC_ARCH}"
export CXX_FOR_HOST = "${CCACHE}${HOST_PREFIX}gcc ${HOST_CC_ARCH}"

export CC_FOR_BUILD = "${BUILD_CC}"
export CPP_FOR_BUILD = "${BUILD_CPP}"
export CFLAGS_FOR_BUILD = "${BUILD_CFLAGS}"

do_configure () {
	(cd ${S}; gnu-configize) || die "Failed to run gnu-configize"
	oe_runconf
#
# must prime config.cache to ensure the build of libiberty
#
	mkdir -p ${B}/build-${BUILD_SYS}
	for i in ${CONFIG_SITE}; do
		cat $i >> ${B}/build-${BUILD_SYS}/config.cache
	done
}

do_install () {
	autotools_do_install

	# We don't really need these, so we'll remove them...
	rm -rf ${D}${libdir}/ldscripts

	# Fix the /usr/${TARGET_SYS}/bin/* links
	for l in ${D}${prefix}/${TARGET_SYS}/bin/*; do
		rm -f $l
		ln -sf `echo ${prefix}/${TARGET_SYS}/bin \
			| tr -s / \
			| sed -e 's,^/,,' -e 's,[^/]*,..,g'`${bindir}/${TARGET_PREFIX}`basename $l` $l
	done

	# Install the libiberty header
	install -d ${D}${includedir}
	install -m 644 ${S}/include/ansidecl.h ${D}${includedir}
	install -m 644 ${S}/include/libiberty.h ${D}${includedir}

	cd ${D}${bindir}

	# Symlinks for ease of running these on the native target
	for p in ${TARGET_PREFIX}* ; do
		ln -sf $p `echo $p | sed -e s,${TARGET_PREFIX},,`
	done

	rm ${D}${bindir}/ar ${D}${bindir}/strings
}

do_install_virtclass-native () {
	autotools_do_install

	# Install the libiberty header
	install -d ${D}${includedir}
	install -m 644 ${S}/include/ansidecl.h ${D}${includedir}
	install -m 644 ${S}/include/libiberty.h ${D}${includedir}

	# We only want libiberty, libbfd and libopcodes
	rm -rf ${D}${bindir}
	rm -rf ${D}${prefix}/${TARGET_SYS}
	rm -rf ${D}${prefix}/lib/ldscripts
	rm -rf ${D}${prefix}/share/info
	rm -rf ${D}${prefix}/share/locale
	rm -rf ${D}${prefix}/share/man
	rmdir ${D}${prefix}/share || :
	rmdir ${D}/${libdir}/gcc-lib || :
	rmdir ${D}/${libdir}64/gcc-lib || :
	rmdir ${D}/${libdir} || :
	rmdir ${D}/${libdir}64 || :
}

pkg_postinst_${PN}-symlinks () {
	update-alternatives --install ${bindir}/ar ar ${TARGET_SYS}-ar 100
	update-alternatives --install ${bindir}/strings strings ${TARGET_SYS}-strings 100
}

pkg_prerm_${PN}-symlinks () {
	update-alternatives --remove ar ${TARGET_SYS}-ar
	update-alternatives --remove strings ${TARGET_SYS}-strings
}
