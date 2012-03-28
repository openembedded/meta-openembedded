inherit cross-canadian

require recipes-devtools/gcc/gcc-${PV}.inc
require recipes-devtools/gcc/gcc-cross-canadian.inc
require recipes-devtools/gcc/gcc-configure-sdk.inc
require recipes-devtools/gcc/gcc-package-sdk.inc


DEPENDS += "gmp-nativesdk mpfr-nativesdk libmpc-nativesdk elfutils-nativesdk"
RDEPENDS_${PN} += "mpfr-nativesdk libmpc-nativesdk elfutils-nativesdk"

SYSTEMHEADERS = "/usr/include"
SYSTEMLIBS = "/lib/"
SYSTEMLIBS1 = "/usr/lib/"

EXTRA_OECONF += "--disable-libunwind-exceptions --disable-libssp \
		--disable-libgomp --disable-libmudflap \
		--with-mpfr=${STAGING_DIR_HOST}${layout_exec_prefix} \
		--with-mpc=${STAGING_DIR_HOST}${layout_exec_prefix}"

# to find libmpfr
# export LD_LIBRARY_PATH = "{STAGING_DIR_HOST}${layout_exec_prefix}"

PARALLEL_MAKE = ""
