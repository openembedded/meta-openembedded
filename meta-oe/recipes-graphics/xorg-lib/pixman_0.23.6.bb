require pixman.inc

# Some artefacts observed in webkit scrolling, need to see if it's a regression or not
DEFAULT_PREFERENCE = "-1"

LICENSE = "MIT & MIT-style & Public Domain"
LIC_FILES_CHKSUM = "file://COPYING;md5=14096c769ae0cbb5fcb94ec468be11b3\
                    file://pixman/pixman-matrix.c;endline=25;md5=ba6e8769bfaaee2c41698755af04c4be \
                    file://pixman/pixman-arm-neon-asm.h;endline=24;md5=9a9cc1e51abbf1da58f4d9528ec9d49b \
                   "

PR = "${INC_PR}.3"

SRC_URI = "http://xorg.freedesktop.org/archive/individual/lib/${BPN}-${PV}.tar.gz \
           file://0001-ARM-NEON-Some-cleanup-of-bilinear-scanline-functions.patch \
           file://0002-ARM-NEON-Bilinear-macro-template-for-instruction-sch.patch \
           file://0003-ARM-NEON-Replace-old-bilinear-scanline-generator-wit.patch \
           file://0004-ARM-NEON-Instruction-scheduling-of-bilinear-over_888.patch \
           file://0005-ARM-NEON-Instruction-scheduling-of-bilinear-over_888.patch \
           file://0006-ARM-NEON-Standard-fast-path-src_n_8_8888.patch \
           file://0007-ARM-NEON-Standard-fast-path-src_n_8_8.patch \
           file://0008-Generic-C-implementation-of-pixman_blt-with-overlapp.patch \
          "

SRC_URI[md5sum] = "27eb7a0ec440c89cccd7c396c3581041"
SRC_URI[sha256sum] = "4e35f49474e78a9430d93caaaea8bbf7e30b65f0da33c31f15a988c25a3ac369"

NEON = " --disable-arm-neon "
NEON_armv7a = " "

EXTRA_OECONF = "${NEON} --disable-gtk"
