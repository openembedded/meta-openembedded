SUMMARY = "Kernel code coverage of a user-space command (KCOV, Intel PT, AMD LBR and Arm Coresight ETMv4)"
DESCRIPTION = "vock runs a command and reports which kernel code it executed, \
using KCOV or hardware tracing (Intel PT, AMD LBR/IBS and Arm Coresight ETMv4), \
optionally with an strace-style syscall trace and syzkaller-compatible output. \
Coverage is symbolized against a vmlinux (DWARF or BTF) and annotated with kernel source."
HOMEPAGE = "https://github.com/yskzalloc/vock"
BUGTRACKER = "https://github.com/yskzalloc/vock/issues"
SECTION = "devel"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=daf2426a488a4a2b68a88bd7cf6410e4"

SRC_URI = "git://github.com/yskzalloc/vock.git;protocol=https;branch=main"
SRCREV = "d36e86cb28ce7e4780f1e6e55797730235b60980"
PV = "0.1.0+git"

inherit cargo cargo-update-recipe-crates

require ${BPN}-crates.inc

# Syscall decoding, ptrace register access and the hardware-trace backends
# are implemented for x86_64 and aarch64 only.
COMPATIBLE_HOST = "(x86_64|aarch64).*-linux"

do_install:append() {
    # LD_PRELOAD shim used by --mode kcov to follow forks and threads. vock
    # looks it up next to its binary as ../lib/vock/kcov.so, i.e. under
    # ${nonarch_libdir} regardless of multilib.
    install -Dm 0755 ${B}/target/${CARGO_TARGET_SUBDIR}/libkcov_preload.so \
        ${D}${nonarch_libdir}/vock/kcov.so
}

FILES:${PN} += "${nonarch_libdir}/vock"
