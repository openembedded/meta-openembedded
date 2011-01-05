PR = "${INC_PR}.1"

require gcc-${PV}.inc
require gcc-configure-target.inc
require gcc-package-target.inc

SRC_URI_append = "file://fortran-cross-compile-hack.patch"

ARCH_FLAGS_FOR_TARGET += "-isystem${STAGING_INCDIR}"

