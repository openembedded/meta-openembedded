require pixman.inc

SRC_URI[archive.md5sum] = "c1a31d5cedfa97c5af7148a2d1fd4356"
SRC_URI[archive.sha256sum] = "9c02c22c6cc3f28f3633d02ef6f0cac130518f621edb011ebbbf08cd1a81251a"

PR = "${INC_PR}.0"

SRC_URI += "\
           file://0000-Add-pixman_bits_override_accessors.patch \
           file://0001-Generic-C-implementation-of-pixman_blt-with-overlapp.patch \
           file://0002-Support-of-overlapping-src-dst-for-pixman_blt_mmx.patch \
           file://0003-Support-of-overlapping-src-dst-for-pixman_blt_sse2.patch \
           file://0004-Support-of-overlapping-src-dst-for-pixman_blt_neon.patch \
           file://0005-ARM-added-NEON-optimizations-for-fetch-store-r5g6b5-.patch \
           file://0006-ARM-added-NEON-optimizations-for-fetch-store-a8-scan.patch \
           file://0007-ARM-added-NEON-optimizations-for-fetching-x8r8g8b8-s.patch \
           file://0008-ARM-optimization-for-scaled-src_0565_0565-operation-.patch \
"

NEON = " --disable-arm-neon "
NEON_armv7a = " "

EXTRA_OECONF = "${NEON} --disable-gtk"
