require pixman.inc

SRC_URI[md5sum] = "e50975ace979cd416a505827c15191b4"
SRC_URI[sha256sum] = "57783330ee2f96121dc267b7f25b98356fd09fe9de185cd39e72e906b6444013"

LICENSE = "MIT & MIT-style & Public Domain"
LIC_FILES_CHKSUM = "file://COPYING;md5=14096c769ae0cbb5fcb94ec468be11b3\
                    file://pixman/pixman-matrix.c;endline=25;md5=ba6e8769bfaaee2c41698755af04c4be \
                    file://pixman/pixman-arm-neon-asm.h;endline=24;md5=9a9cc1e51abbf1da58f4d9528ec9d49b \
                    file://pixman/pixman-x64-mmx-emulation.h;beginline=4;endline=9;md5=4e32716f2efaa6c4659222667c339bb8"

PR = "${INC_PR}.0"

SRC_URI += "\
           file://0017-add-_pixman_bits_override_accessors.patch \
           file://0018-Generic-C-implementation-of-pixman_blt-with-overlapp.patch \
           file://0019-Support-of-overlapping-src-dst-for-pixman_blt_mmx.patch \
           file://0020-Support-of-overlapping-src-dst-for-pixman_blt_sse2.patch \
           file://0021-Support-of-overlapping-src-dst-for-pixman_blt_neon.patch \
           file://0022-ARM-added-NEON-optimizations-for-fetch-store-r5g6b5-.patch \
           file://0023-ARM-added-NEON-optimizations-for-fetch-store-a8-scan.patch \
           file://0024-ARM-added-NEON-optimizations-for-fetching-x8r8g8b8-s.patch \
"

NEON = " --disable-arm-neon "
NEON_armv7a = " "

EXTRA_OECONF = "${NEON} --disable-gtk"
