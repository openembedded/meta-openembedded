SRCREV="71d63ed75648da9b0b71afabb9c60aaad792c55c"

require uclibc.inc
PV = "0.9.31+0.9.32rc3"
PR = "${INC_PR}.1"
PROVIDES += "virtual/${TARGET_PREFIX}libc-for-gcc"

#recent versions uclibc require real kernel headers
PACKAGE_ARCH = "${MACHINE_ARCH}"
FILESPATH = "${@base_set_filespath([ '${FILE_DIRNAME}/uclibc-git' ], d)}"

SRC_URI = "git://uclibc.org/uClibc.git;branch=master;protocol=git \
	file://uClibc.config \
	file://uClibc.machine \
	file://uClibc.distro \
	file://uclibc-arm-ftruncate64.patch \
	file://uclibc_enable_log2_test.patch \
	file://ldso_use_arm_dl_linux_resolve_in_thumb_mode.patch \
	file://reorder-use-BX.patch \
	file://select-force-thumb.patch \
	file://remove-sub-arch-variants.patch \
	file://transform-eabi-oabi-choice.patch \
	file://include-arm-asm.h.patch \
	file://detect-bx-availibility.patch \
	file://remove-eabi-oabi-selection.patch \
	file://powerpc_copysignl.patch \
	file://argp-support.patch \
	file://argp-headers.patch \
	file://remove_attribute_optimize_Os.patch \
	"
S = "${WORKDIR}/git"
