inherit cross-canadian

DESCRIPTION = "A GNU collection of cross-canadian binary utilities for ${TARGET_ARCH} target"
PN = "binutils-cross-canadian-${TRANSLATED_TARGET_ARCH}"
BPN = "binutils"

DEPENDS = "flex-native bison-native virtual/${HOST_PREFIX}gcc-crosssdk virtual/libc-nativesdk zlib-nativesdk gettext-nativesdk"
EXTRA_OECONF = "--with-sysroot=${SDKPATH}/sysroots/${TARGET_SYS} \
                --program-prefix=${TARGET_PREFIX} \
                --disable-werror"

do_install () {
	autotools_do_install

	# We're not interested in the libs or headers, these would come from the 
	# nativesdk or target version of the binutils recipe
	rm -rf ${D}${prefix}/${TARGET_SYS}
	rm -f ${D}${libdir}/libbfd*
	rm -f ${D}${libdir}/libiberty*
	rm -f ${D}${libdir}/libopcodes*
	rm -f ${D}${includedir}/*.h
}
