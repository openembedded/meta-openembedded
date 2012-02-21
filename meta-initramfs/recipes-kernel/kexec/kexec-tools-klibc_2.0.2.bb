# the binaries are statically linked against klibc
require recipes-kernel/kexec/kexec-tools.inc
SRC_URI[md5sum] = "bc401cf3262b25ff7c9a51fc76c8ab91"
SRC_URI[sha256sum] = "549ab65c18a2229b6bf21b6875ca6bbe0e579bca08c3543ce6aaf8287a0b4188"

inherit klibc

FILESPATH =. "${FILE_DIRNAME}/kexec-tools-${PV}:"

SRC_URI += "file://elf.patch \
            file://errno.patch \
            file://string.patch \
            file://syscall.patch \
            file://other.patch \
            "

S = "${WORKDIR}/kexec-tools-${PV}"

EXTRA_OECONF += "--without-zlib"

PACKAGES =+ "kexec-klibc kdump-klibc"

CFLAGS += "-I${STAGING_DIR_HOST}${base_libdir}/klibc/include -I${STAGING_DIR_HOST}${base_libdir}/klibc/include/bits32"

FILES_kexec-klibc = "${sbindir}/kexec"
FILES_kdump-klibc = "${sbindir}/kdump"
