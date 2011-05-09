require pixman.inc

LICENSE = "MIT & MIT-style & Public Domain"
LIC_FILES_CHKSUM = "file://COPYING;md5=14096c769ae0cbb5fcb94ec468be11b3\
                    file://pixman/pixman-matrix.c;endline=25;md5=ba6e8769bfaaee2c41698755af04c4be \
                    file://pixman/pixman-arm-neon-asm.h;endline=24;md5=9a9cc1e51abbf1da58f4d9528ec9d49b \
                   "

PR = "${INC_PR}.0"

SRC_URI = "http://xorg.freedesktop.org/archive/individual/lib/${BPN}-${PV}.tar.gz \
           file://0001-Generic-C-implementation-of-pixman_blt-with-overlapp.patch \
           file://0002-Support-of-overlapping-src-dst-for-pixman_blt_mmx.patch \
           file://0003-Support-of-overlapping-src-dst-for-pixman_blt_sse2.patch \
           file://0004-Support-of-overlapping-src-dst-for-pixman_blt_neon.patch \
"
SRC_URI[md5sum] = "cb8f3cb5ce2c8d7294f73ecb7021fda6"
SRC_URI[sha256sum] = "6b7622256e43912fb77fd456b0753f407268d450f5990c8f86e7c6f006e30da0"

NEON = " --disable-arm-neon "
NEON_armv7a = " "

EXTRA_OECONF = "${NEON} --disable-gtk"
