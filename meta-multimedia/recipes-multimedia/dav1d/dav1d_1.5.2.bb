SUMMARY = "dav1d AV1 decoder"
DESCRIPTION = "Targeted to be small, portable and fast."
HOMEPAGE = "https://code.videolan.org/videolan/dav1d"
SECTION = "multimedia"
LICENSE = "BSD-2-Clause"

LIC_FILES_CHKSUM = "file://COPYING;md5=48d9210a87a24f62609b5a4f777d8dfe"

SRC_URI = "git://code.videolan.org/videolan/dav1d.git;protocol=https;nobranch=1"
SRCREV = "c720f4d3556a01916324aea155555d3efba842e8"

DEPENDS:append:x86 = " nasm-native"
DEPENDS:append:x86-64 = " nasm-native"

inherit meson pkgconfig

# RVV assembler routines are not yet available for RISCV32
EXTRA_OEMESON:append:riscv32 = " -Denable_asm=false"
