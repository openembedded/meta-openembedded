
require recipes-devtools/gcc/gcc-${PV}.inc
require recipes-devtools/gcc/gcc-cross4.inc

EXTRA_OECONF += "--disable-libunwind-exceptions \
                 --with-mpfr=${STAGING_DIR_NATIVE}${prefix_native} \
                 --with-system-zlib "

ARCH_FLAGS_FOR_TARGET += "-isystem${STAGING_DIR_TARGET}${target_includedir}"
