DESCRIPTION = "The Low Level Virtual Machine"
HOMEPAGE = "http://llvm.org"
# 3-clause BSD-like
LICENSE = "NCSA"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=60fdd7739841f04a2ce2171a726be8f3"

DEPENDS = "libffi libxml2-native llvm-common"

SRC_URI = "http://llvm.org/releases/${PV}/llvm-${PV}.src.tar.gz"
SRC_URI_append_libc-uclibc = " file://arm_fenv_uclibc.patch "

SRC_URI[md5sum] = "71610289bbc819e3e15fdd562809a2d7"
SRC_URI[sha256sum] = "125090c4d26740f1d5e9838477c931ed7d9ad70d599ba265f46f3a42cb066343"

S = "${WORKDIR}/llvm-${PV}.src"

inherit autotools perlnative pythonnative

LLVM_BUILD_DIR = "${WORKDIR}/llvm-${PV}.build"
LLVM_INSTALL_DIR = "${WORKDIR}/llvm-install"
LLVM_RELEASE = "3.2"
LLVM_DIR = "llvm${LLVM_RELEASE}"

EXTRA_OECONF += "--disable-assertions \
                 --enable-debug-runtime \
                 --disable-expensive-checks \
                 --enable-bindings=none \
                 --enable-keep-symbols \
                 --enable-libffi \
                 --enable-optimized \
                 --enable-shared \
                 --enable-targets=host-only"
EXTRA_OEMAKE += "REQUIRES_RTTI=1 VERBOSE=1"
FILES_${PN} = "${libdir}/lib*.so \
               ${libdir}/${LLVM_DIR}/*"
FILES_${PN}-dbg = "${bindir}/${LLVM_DIR}/.debug \
                   ${libdir}/${LLVM_DIR}/.debug \
                   ${libdir}/.debug \
                   /usr/src/debug"
FILES_${PN}-dev = "${bindir}/${LLVM_DIR} \
                   ${includedir}/${LLVM_DIR} \
                   ${libdir}/${LLVM_DIR}/BugpointPasses.so \
                   ${libdir}/${LLVM_DIR}/LLVMHello.so"
FILES_${PN}-static-dev = "${libdir}/${LLVM_DIR}/*.a"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"

do_configure_prepend() {
	# Remove RPATHs
	sed -i 's:$(RPATH) -Wl,$(\(ToolDir\|LibDir\|ExmplDir\))::g' Makefile.rules

	# Drop "svn" suffix from version string
	sed -i 's/3\.2svn/3.2/g' configure

	# Fix paths in llvm-config
	sed -i "s|sys::path::parent_path(CurrentPath))\.str()|sys::path::parent_path(sys::path::parent_path(CurrentPath))).str()|g" tools/llvm-config/llvm-config.cpp
	sed -ri "s#/(bin|include|lib)(/?\")#/\1/${LLVM_DIR}\2#g" tools/llvm-config/llvm-config.cpp

	# Fails to build unless using separate directory from source
	mkdir -p ${LLVM_BUILD_DIR}
	cd ${LLVM_BUILD_DIR}
}

do_compile() {
	cd ${LLVM_BUILD_DIR}
	oe_runmake \
		AR="${BUILD_AR}" \
		CC="${BUILD_CC}" \
		CFLAGS="${BUILD_CFLAGS}" \
		CXX="${BUILD_CXX}" \
		CXXFLAGS="${BUILD_CXXFLAGS}" \
		CPP="${BUILD_CPP}" \
		CPPFLAGS="${BUILD_CPPFLAGS}" \
		NM="${BUILD_NM}" \
		RANLIB="${BUILD_RANLIB}" \
		PATH="${STAGING_BINDIR_NATIVE}:$PATH" \
		cross-compile-build-tools
	oe_runmake
}

do_install() {
	cd ${LLVM_BUILD_DIR}
	oe_runmake DESTDIR=${LLVM_INSTALL_DIR} install

	mv ${LLVM_INSTALL_DIR}${bindir}/${HOST_SYS}-llvm-config-host ${LLVM_INSTALL_DIR}/llvm-config-host

	install -d ${D}${bindir}/${LLVM_DIR}
	mv ${LLVM_INSTALL_DIR}${bindir}/* ${D}${bindir}/${LLVM_DIR}/

	install -d ${D}${includedir}/${LLVM_DIR}
	mv ${LLVM_INSTALL_DIR}${includedir}/* ${D}${includedir}/${LLVM_DIR}/

	install -d ${D}${libdir}/${LLVM_DIR}
	mv ${LLVM_INSTALL_DIR}${libdir}/* ${D}${libdir}/${LLVM_DIR}/
	ln -s ${LLVM_DIR}/libLLVM-${LLVM_RELEASE}.so ${D}${libdir}/libLLVM-${LLVM_RELEASE}.so

	install -d ${D}${docdir}/${LLVM_DIR}
	mv ${LLVM_INSTALL_DIR}${prefix}/docs/llvm/* ${D}${docdir}/${LLVM_DIR}
}

SYSROOT_PREPROCESS_FUNCS += "llvm_sysroot_preprocess"

llvm_sysroot_preprocess() {
	install -d ${SYSROOT_DESTDIR}${bindir_crossscripts}
	mv ${LLVM_INSTALL_DIR}/llvm-config-host ${SYSROOT_DESTDIR}${bindir_crossscripts}/llvm-config${LLVM_RELEASE}
}
