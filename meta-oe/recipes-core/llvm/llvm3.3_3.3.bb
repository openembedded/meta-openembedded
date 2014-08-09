require llvm.inc

DEPENDS += "zlib"
EXTRA_OECONF += "--enable-zlib"

SRC_URI_append_libc-uclibc = " file://arm_fenv_uclibc.patch "
SRC_URI[md5sum] = "40564e1dc390f9844f1711c08b08e391"
SRC_URI[sha256sum] = "68766b1e70d05a25e2f502e997a3cb3937187a3296595cf6e0977d5cd6727578"

PACKAGECONFIG ??= ""
PACKAGECONFIG[r600] = "--enable-experimental-targets=R600,,,"
