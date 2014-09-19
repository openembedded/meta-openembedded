DESCRIPTION = "klibc is intended to be a minimalistic libc subset for \
use with initramfs.  It is deliberately written for small size, \
minimal entaglement, and portability, not speed."
SECTION = "libs"
LICENSE = "BSD-3-Clause & GPL-2.0 & MIT & Zlib"
LIC_FILES_CHKSUM = "file://usr/klibc/LICENSE;md5=d75181f10e998c21eb147f6d2e43ce8b"
DEPENDS = "linux-libc-headers perl-native"
SRCREV = "7763dd33e5b8eed4b9e3c583c02c10176fd550d3"

SRC_URI = "git://git.kernel.org/pub/scm/libs/klibc/klibc.git"
SRC_URI_append_linux-gnueabi = " file://klibc-config-eabi.patch \
                                 file://armv4-fix-v4bx.patch \
                                 "
SRC_URI_append_linux-uclibceabi = " file://klibc-config-eabi.patch \
                                    file://armv4-fix-v4bx.patch \
                                    "
SRC_URI += "file://klibc-linux-libc-dev.patch \
            file://staging.patch \
            file://klcc-consider-sysroot.patch \
            "

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "'KLIBCARCH=${KLIBC_ARCH}' \
                'CROSS_COMPILE=${TARGET_PREFIX}' \
                'KLIBCKERNELSRC=${STAGING_DIR_TARGET}${exec_prefix}' \
                'prefix=${exec_prefix}' \
                'INSTALLROOT=${D}' \
                'SHLIBDIR=${libdir}' \
                "

export FIX_ARMV4_EABI_BX = "${FIX_V4BX}"

do_configure () {
    ln -sf "${STAGING_DIR_TARGET}${exec_prefix}" linux
}

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
KLIBC_ARCH = "${TARGET_ARCH}"
KLIBC_ARCH_aarch64 = "arm64"
KLIBC_ARCH_armeb = "arm"
KLIBC_ARCH_mipsel = "mips"
KLIBC_ARCH_x86 = "i386"
KLIBC_ARCH_x86-64 = "x86_64"
KLIBC_ARCH_i486 = "i386"
KLIBC_ARCH_i586 = "i386"
KLIBC_ARCH_i686 = "i386"
KLIBC_ARCH_pentium = "i386"
KLIBC_ARCH_powerpc = "ppc"
KLIBC_ARCH_powerpc64 = "ppc64"
THIS_LIBKLIBC = "libklibc (= ${PV})"
