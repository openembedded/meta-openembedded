# Cut-down gcc for kernel builds
# Only installs ${TARGET_PREFIX}gcc-${PV}, not ${TARGET_PREFIX}gcc.

PROVIDES = "virtual/${TARGET_PREFIX}gcc-${PV}"

do_install () {
	cd gcc
	oe_runmake 'DESTDIR=${D}' install-common install-headers install-libgcc
	install -m 0755 xgcc ${D}${STAGING_DIR_NATIVE}${prefix_native}/bin/${TARGET_PREFIX}gcc-${PV}
}
