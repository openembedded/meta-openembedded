SUMMARY = "Fast, multi-threaded malloc() and nifty performance analysis tools"
HOMEPAGE = "http://code.google.com/p/gperftools/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=762732742c73dc6c7fbe8632f06c059a"
DEPENDS += "libunwind"

SRCREV = "bf840dec0495e17f5c8403e68e10b9d6bf05c559"
SRC_URI = "git://github.com/gperftools/gperftools \
           file://0001-Support-Atomic-ops-on-clang.patch \
           file://0001-Use-ucontext_t-instead-of-struct-ucontext.patch \
           file://0001-fix-build-with-musl-libc.patch \
           file://0001-include-fcntl.h-for-loff_t-definition.patch \
           "

inherit autotools

S = "${WORKDIR}/git"

# On mips, we have the following error.
#   do_page_fault(): sending SIGSEGV to ls for invalid read access from 00000008
#   Segmentation fault (core dumped)
COMPATIBLE_HOST = "(i.86|x86_64|powerpc|powerpc64|arm|aarch64).*-linux*"
# On aarch64, add this option to avoid system hanging when using libtcmalloc.so.
EXTRA_OECONF_aarch64 += "--disable-libunwind"
# Disable thumb1
# {standard input}: Assembler messages:
# {standard input}:434: Error: lo register required -- `ldr pc,[sp]'
# Makefile:4538: recipe for target 'src/base/libtcmalloc_la-linuxthreads.lo' failed
ARM_INSTRUCTION_SET_armv5 = "arm"

