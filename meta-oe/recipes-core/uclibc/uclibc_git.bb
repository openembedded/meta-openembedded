# UCLIBC_BASE should be the latest released version of uclibc (that way
# the config files will typically be correct!)  uclibc-svn takes precedence
# over uclibc-${UCLIBC_BASE}, if a config file in uclibc-snv is out of date
# try removing it
#
# UCLIBC_BASE can be set in a distro file, but whether this works depends
# on whether the base patches apply to the selected (SRCDATE) svn release.
#
UCLIBC_BASE ?= "0.9.32"
SRCREV="074930dd7f2a18a7013cdb72cc38136f3be39c40"
PR_append = "+gitr${SRCPV}"

require uclibc.inc
PR = "${INC_PR}.1"
PROVIDES += "virtual/${TARGET_PREFIX}libc-for-gcc"

#recent versions uclibc require real kernel headers
PACKAGE_ARCH = "${MACHINE_ARCH}"
FILESPATHPKG =. "uclibc-git:uclibc-${UCLIBC_BASE}:"

#as stated above, uclibc needs real kernel-headers
#however: we can't depend on virtual/kernel when nptl hits due to depends deadlocking ....
KERNEL_SOURCE = "${STAGING_DIR_HOST}/${exec_prefix}"
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
	file://uclibc-epoll.patch \
	"
S = "${WORKDIR}/git"
