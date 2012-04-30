require recipes-devtools/gcc/gcc-cross-intermediate_${PV}.bb
require recipes-devtools/gcc/gcc-crosssdk-intermediate.inc
EXTRA_OECONF += " --with-headers=${STAGING_DIR_TCBOOTSTRAP}${SYSTEMHEADERS} "
