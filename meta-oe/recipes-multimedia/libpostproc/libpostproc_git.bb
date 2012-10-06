DESCRIPTION = "FFmpeg derived postprocessing library"
HOMEPAGE = "http://git.videolan.org/?p=libpostproc.git;a=summary"
SECTION = "libs"
DEPENDS = "libav"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "0.0.0+git${SRCPV}"

DEFAULT_PREFERENCE = "-1"

SRCREV = "3b7053f46dbfe4662063345245cb00b6acbbe969"
SRC_URI = "git://git.videolan.org/libpostproc.git"

S = "${WORKDIR}/git"

inherit autotools lib_package pkgconfig

FULL_OPTIMIZATION_armv7a = "-fexpensive-optimizations -fomit-frame-pointer -O4 -ffast-math"
BUILD_OPTIMIZATION = "${FULL_OPTIMIZATION}"

EXTRA_FFCONF_armv7a = "--cpu=cortex-a8"
EXTRA_FFCONF ?= ""

EXTRA_OECONF = " \
        --enable-shared \
        --enable-pthreads \
        --enable-gpl \
        --enable-postproc \
        \
        --cross-prefix=${TARGET_PREFIX} \
        --prefix=${prefix} \
        \
        --arch=${TARGET_ARCH} \
        --target-os="linux" \
        --enable-cross-compile \
        --extra-cflags="${TARGET_CFLAGS} ${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS}" \
        --extra-ldflags="${TARGET_LDFLAGS}" \
        --sysroot="${STAGING_DIR_TARGET}" \
        ${EXTRA_FFCONF} \
"

#| yasm not found, use --disable-yasm for a crippled build
EXTRA_OECONF_append_x86-64 = " --disable-yasm"
EXTRA_OECONF_append_x86 = " --disable-yasm"

do_configure() {
    ./configure ${EXTRA_OECONF}
}
