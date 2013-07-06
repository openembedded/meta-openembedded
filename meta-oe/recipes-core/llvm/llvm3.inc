DEPENDS = "libffi libxml2-native llvm-common"

SRC_URI = "http://llvm.org/releases/${PV}/llvm-${PV}.src.tar.gz"
S = "${WORKDIR}/llvm-${PV}.src"

inherit autotools

LLVM_BUILD_DIR = "${WORKDIR}/llvm-${PV}.build"
LLVM_INSTALL_DIR = "${WORKDIR}/llvm-install"

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

do_configure_prepend() {
    # Remove RPATHs
    sed -i 's:$(RPATH) -Wl,$(\(ToolDir\|LibDir\|ExmplDir\))::g' Makefile.rules

    # Drop "svn" suffix from version string
    sed -i 's/${PV}svn/${PV}/g' configure

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
    ln -s ${LLVM_DIR}/libLLVM-${PV}.so ${D}${libdir}/libLLVM-${PV}.so

    install -d ${D}${docdir}/${LLVM_DIR}
    mv ${LLVM_INSTALL_DIR}${prefix}/docs/llvm/* ${D}${docdir}/${LLVM_DIR}
}

SYSROOT_PREPROCESS_FUNCS += "llvm_sysroot_preprocess"

llvm_sysroot_preprocess() {
    install -d ${SYSROOT_DESTDIR}${bindir_crossscripts}
    mv ${LLVM_INSTALL_DIR}/llvm-config-host ${SYSROOT_DESTDIR}${bindir_crossscripts}/llvm-config${PV}
}
