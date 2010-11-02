inherit crosssdk

SYSTEMHEADERS = "${SDKPATHNATIVE}${prefix_nativesdk}/include"
SYSTEMLIBS = "${SDKPATHNATIVE}${base_libdir_nativesdk}/"
SYSTEMLIBS1 = "${SDKPATHNATIVE}${libdir_nativesdk}/"

GCCMULTILIB = "--disable-multilib"

DEPENDS = "virtual/${TARGET_PREFIX}binutils-crosssdk virtual/${TARGET_PREFIX}libc-for-gcc-nativesdk gettext-native"
PROVIDES = "virtual/${TARGET_PREFIX}gcc-crosssdk virtual/${TARGET_PREFIX}g++-crosssdk"

do_configure_prepend () {
	# Change the default dynamic linker path to the one in the SDK
	sed -i ${S}/gcc/config/*/linux*.h -e 's#\(GLIBC_DYNAMIC_LINKER.*\)/lib/#\1${SYSTEMLIBS}#'
	sed -i ${S}/gcc/config/*/linux*.h -e 's#\(GLIBC_DYNAMIC_LINKER.*\)/lib64/#\1${SYSTEMLIBS}#'
}
