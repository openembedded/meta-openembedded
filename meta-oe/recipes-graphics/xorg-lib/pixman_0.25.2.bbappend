FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"

DEPENDS += "perl-native"
BBCLASSEXTEND += "native"

PRINC := "${@int(PRINC) + 8}"

SRC_URI += " file://0008-Generic-C-implementation-of-pixman_blt-with-overlapp.patch"

NEON = " --disable-arm-neon "
NEON_armv7a = " "
NEON_armv7a-vfp-neon = " "

EXTRA_OECONF += "${NEON}"
