
require recipes-devtools/gcc/gcc-${PV}.inc
require recipes-devtools/gcc/gcc-configure-target.inc
require recipes-devtools/gcc/gcc-package-target.inc

SRC_URI_append = "file://fortran-cross-compile-hack.patch"

ARCH_FLAGS_FOR_TARGET += "-isystem${STAGING_INCDIR}"

