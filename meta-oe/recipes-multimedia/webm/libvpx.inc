DESCRIPTION = "vpx Multi-Format Codec SDK"
LICENSE = "BSD"

INC_PR = "r3"

SRC_URI = "http://webm.googlecode.com/files/libvpx-v${PV}.tar.bz2"
S = "${WORKDIR}/libvpx-v${PV}"

# ffmpeg links with this and fails 
# sysroots/armv4t-oe-linux-gnueabi/usr/lib/libvpx.a(vpx_encoder.c.o)(.text+0xc4): unresolvable R_ARM_THM_CALL relocation against symbol `memcpy@@GLIBC_2.4'
ARM_INSTRUCTION_SET = "arm"

CFLAGS += "-fPIC"

export CC
export LD = "${CC}"

VPXTARGET_armv5te = "armv5te-linux-gcc"
VPXTARGET_armv6 = "armv6-linux-gcc"
VPXTARGET_armv7a = "armv7-linux-gcc"
VPXTARGET ?= "generic-gnu"

CONFIGUREOPTS = " \
          --target=${VPXTARGET} \
          --enable-vp8 \
          --enable-libs \
          --disable-install-docs \
"
do_configure() {
	${S}/configure ${CONFIGUREOPTS}
}
do_compile() {
	oe_runmake
}
do_install() {
	oe_runmake install DESTDIR=${D}
}

