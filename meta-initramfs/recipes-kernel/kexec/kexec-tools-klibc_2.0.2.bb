# the binaries are statically linked against klibc
require recipes-kernel/kexec/kexec-tools.inc
SRC_URI[md5sum] = "92eff93b097475b7767f8c98df84408a"
SRC_URI[sha256sum] = "09e180ff36dee087182cdc939ba6c6917b6adbb5fc12d589f31fd3659b6471f2"

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
