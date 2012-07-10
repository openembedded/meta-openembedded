FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"

PRINC := "${@int(PRINC) + 9}"

SRC_URI += " file://0008-Generic-C-implementation-of-pixman_blt-with-overlapp.patch"

NEON = " --disable-arm-neon "
NEON_armv7a = " "
NEON_armv7a-vfp-neon = " "

EXTRA_OECONF += "${NEON}"
