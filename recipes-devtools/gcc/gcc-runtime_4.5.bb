PR = "r10"

require gcc-${PV}.inc
require gcc-configure-runtime.inc
require gcc-package-runtime.inc

SRC_URI_append = "file://fortran-cross-compile-hack.patch"

ARCH_FLAGS_FOR_TARGET += "-isystem${STAGING_INCDIR}"

EXTRA_OECONF += "--disable-libunwind-exceptions"
