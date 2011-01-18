require gcc-configure-common.inc

EXTRA_OECONF_PATHS = " \
    --with-local-prefix=${STAGING_DIR_TARGET}${prefix} \
    --with-gxx-include-dir=${includedir}/c++/ \
    --with-sysroot=${STAGING_DIR_TARGET} \
    --with-build-sysroot=${STAGING_DIR_TARGET}"

RUNTIMETARGET = "libssp libstdc++-v3"
#  ?
# libiberty
# libmudflap
# libgfortran

do_configure () {
	export CXX="${CXX} -nostdinc++ -nostdlib++"
	for d in ${RUNTIMETARGET}; do
		echo "Configuring $d"
		mkdir -p ${B}/$d/
		cd ${B}/$d/
		chmod a+x ${S}/$d/configure
		${S}/$d/configure ${CONFIGUREOPTS} ${EXTRA_OECONF}
	done
}

do_compile () {
	for d in ${RUNTIMETARGET}; do
		cd ${B}/$d/
		oe_runmake
	done
}

do_install () {
	for d in ${RUNTIMETARGET}; do
		cd ${B}/$d/
		oe_runmake 'DESTDIR=${D}' install
	done
}

INHIBIT_DEFAULT_DEPS = "1"
DEPENDS = "virtual/${TARGET_PREFIX}gcc virtual/${TARGET_PREFIX}g++ libgcc"
PROVIDES = "virtual/${TARGET_PREFIX}compilerlibs"

BBCLASSEXTEND = "nativesdk"

