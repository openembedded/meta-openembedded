INHIBIT_PACKAGE_STRIP = "1"

# Having anything auto depending on gcc-cross-sdk is a really bad idea...
EXCLUDE_FROM_SHLIBS = "1"

PACKAGES = "${PN} ${PN}-doc"

FILES_${PN} = "\
    ${bindir}/* \
    ${libexecdir}/gcc/${TARGET_SYS}/${BINV}/* \
    ${gcclibdir}/${TARGET_SYS}/${BINV}/*.o \
    ${gcclibdir}/${TARGET_SYS}/${BINV}/specs \
    ${gcclibdir}/${TARGET_SYS}/${BINV}/lib* \
    ${gcclibdir}/${TARGET_SYS}/${BINV}/include \
    ${gcclibdir}/${TARGET_SYS}/${BINV}/include-fixed \
    ${includedir}/c++/${BINV} \
    ${prefix}/${TARGET_SYS}/bin/* \
    ${prefix}/${TARGET_SYS}/lib/* \
    ${prefix}/${TARGET_SYS}/usr/include/* \
    "
FILES_${PN}-doc = "\
    ${infodir} \
    ${mandir} \
    ${gcclibdir}/${TARGET_SYS}/${BINV}/include/README \
    "

do_install () {
	oe_runmake 'DESTDIR=${D}' install-host

	# Cleanup some of the ${libdir}{,exec}/gcc stuff ...
	rm -r ${D}${libdir}/gcc/${TARGET_SYS}/${BINV}/install-tools
	rm -r ${D}${libexecdir}/gcc/${TARGET_SYS}/${BINV}/install-tools

	# We care about g++ not c++
	rm -f ${D}${bindir}/*c++

	# We don't care about the gcc-<version> copies
	rm -f ${D}${bindir}/*gcc-?.?*

	# We use libiberty from binutils
	rm -f ${D}${prefix}/${TARGET_SYS}/lib/libiberty.a
	rm -f ${D}${libdir}/libiberty.a

	# Insert symlinks into libexec so when tools without a prefix are searched for, the correct ones are
	# found.
	dest=${D}${libexecdir}/gcc/${TARGET_SYS}/${BINV}/
	install -d $dest
	for t in ar as ld nm objcopy objdump ranlib strip g77 gcc cpp gfortran; do
		ln -sf ${bindir}/${TARGET_PREFIX}$t $dest$t
	done
}

