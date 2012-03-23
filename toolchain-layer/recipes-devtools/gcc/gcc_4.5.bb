
require recipes-devtools/gcc/gcc-${PV}.inc
require recipes-devtools/gcc/gcc-configure-target.inc
require recipes-devtools/gcc/gcc-package-target.inc

ARCH_FLAGS_FOR_TARGET += "-isystem${STAGING_INCDIR}"

