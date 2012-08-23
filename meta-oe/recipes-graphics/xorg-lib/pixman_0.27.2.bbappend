FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"

PRINC := "${@int(PRINC) + 1}"

SRC_URI += " \
  file://0001-ARM-qemu-related-workarounds-in-cpu-features-detecti.patch \
  file://0002-Generic-C-implementation-of-pixman_blt-with-overlapp.patch \
"

NEON = " --disable-arm-neon "
NEON_armv7a = " "
NEON_armv7a-vfp-neon = " "

EXTRA_OECONF += "${NEON}"
