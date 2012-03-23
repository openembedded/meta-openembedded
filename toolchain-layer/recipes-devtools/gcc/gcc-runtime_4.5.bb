
require recipes-devtools/gcc/gcc-${PV}.inc
require recipes-devtools/gcc/gcc-configure-runtime.inc
require recipes-devtools/gcc/gcc-package-runtime.inc

ARCH_FLAGS_FOR_TARGET += "-isystem${STAGING_INCDIR}"

EXTRA_OECONF += "--disable-libunwind-exceptions"
