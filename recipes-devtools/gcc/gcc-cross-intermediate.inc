DEPENDS = "virtual/${TARGET_PREFIX}binutils ${NATIVEDEPS}"
DEPENDS += "virtual/${TARGET_PREFIX}libc-initial gettext-native"
PROVIDES = "virtual/${TARGET_PREFIX}gcc-intermediate"
PACKAGES = ""

# This is intended to be a -very- basic config
# sysroot is needed in case we use libc-initial
EXTRA_OECONF = "--with-local-prefix=${STAGING_DIR_TARGET}${target_prefix} \
		--enable-shared \
		--disable-multilib \
		--disable-threads \
		--enable-languages=c \
                ${OPTSPACE} \
		--program-prefix=${TARGET_PREFIX} \
		--with-sysroot=${STAGING_DIR_TARGET} \
		--with-build-sysroot=${STAGING_DIR_TARGET} \
		${EXTRA_OECONF_INTERMEDIATE} \
		${@get_gcc_fpu_setting(bb, d)}"

do_compile () {
    oe_runmake
}

do_install () {
	oe_runmake 'DESTDIR=${D}' install
	install -d ${D}${target_base_libdir}/
	mv ${D}${exec_prefix}/${TARGET_SYS}/lib/* ${D}${target_base_libdir}/
}
