SUMMARY = "A utility to print stack traces of running processes"
DESCRIPTION = "A from-scratch implementation of pstack using DWARF debugging and unwind information. \
Works for C/C++, Go, Rust, and Python. A traditional pstack command can generally print a backtrace \
of each thread in a running program, and sometimes from a core file. This version of pstack uses its \
own self contained ELF and DWARF parsing library, libdwelf to parse the DWARF debug and unwind \
information, to get a stack trace."
HOMEPAGE = "https://github.com/peadar/pstack"
SECTION = "devel"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=671019a96ba80415b696240ed2ca5e80"

inherit cmake

DEPENDS += "zlib xz libunwind elfutils"

RDEPENDS:${PN} += "elfutils"

EXTRA_OECMAKE += "-DCMAKE_SKIP_INSTALL_RPATH=ON"

# libhdbg.so is an unversioned LD_PRELOAD heap debugging library
PACKAGES =+ "${PN}-hdbg"
FILES:${PN}-hdbg = "${libdir}/libhdbg.so"

FILES:${PN} += "${datadir}/pstack"

TOOLCHAIN = "gcc"

COMPATIBLE_HOST:riscv64 = "null"
COMPATIBLE_HOST:riscv32 = "null"
COMPATIBLE_HOST:arm = "null"
COMPATIBLE_HOST:libc-musl = "null"
