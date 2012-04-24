DEPENDS = "virtual/kernel perl-native"
SECTION = "libs"
DESCRIPTION = "klibc is intended to be a minimalistic libc subset for \
use with initramfs.  It is deliberately written for small size, \
minimal entaglement, and portability, not speed."
LICENSE = "BSD-3-Clause & GPL-2.0 & MIT & Zlib"
LIC_FILES_CHKSUM = "file://usr/klibc/LICENSE;md5=d75181f10e998c21eb147f6d2e43ce8b"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Prevents do_package failures with:
# debugsources.list: No such file or directory:
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

INC_PR = "r3"

KLIBC_ARCH = '${TARGET_ARCH}'
KLIBC_ARCH_armeb = 'arm'
KLIBC_ARCH_mipsel = 'mips'
KLIBC_ARCH_x86 = 'i386'
KLIBC_ARCH_i486 = 'i386'
KLIBC_ARCH_i586 = 'i386'
KLIBC_ARCH_i686 = 'i386'
KLIBC_ARCH_pentium = 'i386'

SRCREV = "ff0a614bd724f6c4c6a5014a9955dc1bc028f336"
SRC_URI = "git://git.kernel.org/pub/scm/libs/klibc/klibc.git;protocol=git"

SRC_URI_append_linux-gnueabi = " file://klibc-config-eabi.patch"
SRC_URI_append_linux-uclibceabi = " file://klibc-config-eabi.patch"

SRC_URI += "file://fstype-sane-vfat-and-jffs2-for-1.5.patch \
            file://modprobe.patch \
            file://dash_readopt.patch \
            file://wc.patch \
            file://staging.patch \
            file://kernel_3.2__aligned_u64.patch  \
            "

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "'KLIBCARCH=${KLIBC_ARCH}' \
                'CROSS_COMPILE=${TARGET_PREFIX}' \
                'KLIBCKERNELSRC=${STAGING_KERNEL_DIR}' \
                "

do_configure () {
        ln -sf ${STAGING_KERNEL_DIR} linux
}
