DESCRIPTION = "vpx Multi-Format Codec SDK"
LICENSE = "BSD"

INC_PR = "r2"

SRC_URI = "http://webm.googlecode.com/files/libvpx-v${PV}.tar.bz2"
S = "${WORKDIR}/libvpx-v${PV}"

# ffmpeg links with this and fails 
# sysroots/armv4t-oe-linux-gnueabi/usr/lib/libvpx.a(vpx_encoder.c.o)(.text+0xc4): unresolvable R_ARM_THM_CALL relocation against symbol `memcpy@@GLIBC_2.4'
ARM_INSTRUCTION_SET = "arm"

#-ftree-vectorize was causing ICE with gcc-4.6 from meta-oe, see
# http://lists.linuxtogo.org/pipermail/openembedded-devel/2011-November/036001.html
#|     [CC] vpx_scale/generic/gen_scalers.c.o
#| vpx_scale/generic/gen_scalers.c: In function 'vp8cx_vertical_band_3_4_scale_c':
#| vpx_scale/generic/gen_scalers.c:537:6: internal compiler error: in vect_get_vec_def_for_stmt_copy, at tree-vect-stmts.c:1390
#| Please submit a full bug report,
#| with preprocessed source if appropriate.
#| See <http://gcc.gnu.org/bugs.html> for instructions.
#| make[1]: *** [vpx_scale/generic/gen_scalers.c.o] Error 1
#| make: *** [.DEFAULT] Error 2

CFLAGS += "-fPIC -fno-tree-vectorize"

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

