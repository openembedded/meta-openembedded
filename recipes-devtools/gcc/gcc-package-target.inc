PACKAGES = "\
  ${PN} ${PN}-symlinks \
  g++ g++-symlinks \
  cpp cpp-symlinks \
  g77 g77-symlinks \
  gfortran gfortran-symlinks \
  gcov gcov-symlinks \
  ${PN}-doc \
"

FILES_${PN} = "\
  ${bindir}/${TARGET_PREFIX}gcc \
  ${bindir}/${TARGET_PREFIX}gccbug \
  ${libexecdir}/gcc/${TARGET_SYS}/${BINV}/collect2 \
  ${gcclibdir}/${TARGET_SYS}/${BINV}/*.o \
  ${gcclibdir}/${TARGET_SYS}/${BINV}/specs \
  ${gcclibdir}/${TARGET_SYS}/${BINV}/lib* \
  ${gcclibdir}/${TARGET_SYS}/${BINV}/include \
  ${gcclibdir}/${TARGET_SYS}/${BINV}/include-fixed \
"
FILES_${PN}-symlinks = "\
  ${bindir}/cc \
  ${bindir}/gcc \
  ${bindir}/gccbug \
"

FILES_g77 = "\
  ${bindir}/${TARGET_PREFIX}g77 \
  ${libexecdir}/gcc/${TARGET_SYS}/${BINV}/f771 \
"
FILES_g77-symlinks = "\
  ${bindir}/g77 \
  ${bindir}/f77 \
"
FILES_gfortran = "\
  ${bindir}/${TARGET_PREFIX}gfortran \
  ${libexecdir}/gcc/${TARGET_SYS}/${BINV}/f951 \
"
FILES_gfortran-symlinks = "\
  ${bindir}/gfortran \
  ${bindir}/f95"

FILES_cpp = "\
  ${bindir}/${TARGET_PREFIX}cpp \
  ${base_libdir}/cpp \
  ${libexecdir}/gcc/${TARGET_SYS}/${BINV}/cc1"
FILES_cpp-symlinks = "${bindir}/cpp"

FILES_gcov = "${bindir}/${TARGET_PREFIX}gcov"
FILES_gcov-symlinks = "${bindir}/gcov"

FILES_g++ = "\
  ${bindir}/${TARGET_PREFIX}g++ \
  ${libexecdir}/gcc/${TARGET_SYS}/${BINV}/cc1plus \
"
FILES_g++-symlinks = "\
  ${bindir}/c++ \
  ${bindir}/g++ \
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

	# Hack around specs file assumptions
	test -f ${D}${libdir}/gcc/${TARGET_SYS}/${BINV}/specs && sed -i -e '/^*cross_compile:$/ { n; s/1/0/; }' ${D}${libdir}/gcc/${TARGET_SYS}/${BINV}/specs

	# Cleanup manpages..
	rm -rf ${D}${mandir}/man7

	cd ${D}${bindir}

	# We care about g++ not c++
	rm -f *c++

	# We don't care about the gcc-<version> ones for this
	rm -f *gcc-?.?*

	# Symlinks so we can use these trivially on the target
	ln -sf ${TARGET_SYS}-g77 g77 || true
	ln -sf ${TARGET_SYS}-gfortran gfortran || true
	ln -sf ${TARGET_SYS}-g++ g++
	ln -sf ${TARGET_SYS}-gcc gcc
	ln -sf g77 f77 || true
	ln -sf gfortran f95 || true
	ln -sf g++ c++
	ln -sf gcc cc
	ln -sf ${bindir}/${TARGET_SYS}-cpp ${D}${bindir}/cpp
}
