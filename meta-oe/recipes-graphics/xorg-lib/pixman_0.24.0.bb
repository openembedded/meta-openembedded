require pixman.inc

LICENSE = "MIT & MIT-style & Public Domain"
LIC_FILES_CHKSUM = "file://COPYING;md5=14096c769ae0cbb5fcb94ec468be11b3\
                    file://pixman/pixman-matrix.c;endline=25;md5=ba6e8769bfaaee2c41698755af04c4be \
                    file://pixman/pixman-arm-neon-asm.h;endline=24;md5=9a9cc1e51abbf1da58f4d9528ec9d49b \
                   "

PR = "${INC_PR}.0"

SRC_URI = "http://xorg.freedesktop.org/archive/individual/lib/${BPN}-${PV}.tar.gz \
           file://0008-Generic-C-implementation-of-pixman_blt-with-overlapp.patch \
          "

SRC_URI[md5sum] = "a2d0b120509bdccb10aa7f4bec3730e4"
SRC_URI[sha256sum] = "a5647c7158f103eedff5fba799018f4169f6b26b573ab7685812ebc9a1c5d2e4"

NEON = " --disable-arm-neon "
NEON_armv7a = " "

EXTRA_OECONF = "${NEON} --disable-gtk"
