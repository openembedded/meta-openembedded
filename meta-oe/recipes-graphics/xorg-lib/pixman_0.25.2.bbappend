FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"

DEPENDS += "perl-native"
BBCLASSEXTEND += "native"

PRINC = "7"

SRC_URI += " file://0008-Generic-C-implementation-of-pixman_blt-with-overlapp.patch"

NEON = " --disable-arm-neon "
NEON_armv7a = " "
NEON_armv7a-vfp-neon = " "

IWMMXT = " --disable-arm-iwmmxt "

EXTRA_OECONF += "${NEON} ${IWMMXT}"
