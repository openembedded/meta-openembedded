inherit crosssdk

SYSTEMHEADERS = "${SDKPATHNATIVE}${prefix_nativesdk}/include"
SYSTEMLIBS = "${SDKPATHNATIVE}${base_libdir_nativesdk}/"
SYSTEMLIBS1 = "${SDKPATHNATIVE}${libdir_nativesdk}/"

DEPENDS = "virtual/${TARGET_PREFIX}binutils-crosssdk gettext-native"
DEPENDS += "virtual/${TARGET_PREFIX}libc-initial-nativesdk"
PROVIDES = "virtual/${TARGET_PREFIX}gcc-intermediate-crosssdk"
