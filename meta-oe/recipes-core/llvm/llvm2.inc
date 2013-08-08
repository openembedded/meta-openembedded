DEPENDS = "llvm${LLVM_RELEASE}-native llvm-common perl-native"
DEPENDS_class-native = "llvm-common-native cmake-native"

SRC_URI = "http://llvm.org/releases/${PV}/llvm-${PV}.tgz"
S = "${WORKDIR}/llvm-${PV}"

LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=0ac5f799a2d89477c75b0a378b221855"

inherit cmake

# Defines the LLVM supported arches. By now we always build either for ${BUILD}
# (native) or ${TARGET}. In the future it may make sense to enable all backends
# for the non-native build. The decision which backends are used is made by
# the 3rd party program or library that uses llvm anyway.
LLVM_ARCH = "${@get_llvm_arch(d)}"

# This is used for generating the install directory for the llvm libraries,
# binaries and headers. It makes side by side installation of those possible.
LLVM_RELEASE = "${PV}"

BBCLASSEXTEND = "native"

LLVM_BUILD_DIR = "${B}/build"
LLVM_INSTALL_DIR = "${B}/llvm-install"

# llvm *must* be built out of tree
OECMAKE_SOURCEPATH = ".."
OECMAKE_BUILDPATH = "build"

# other architectures require X86 available,
LLVM_EXTRA_ARCH = "X86;"
LLVM_EXTRA_ARCH_x86 = ""
LLVM_EXTRA_ARCH_x86-64 = ""

EXTRA_OECMAKE = "\
    -DLLVM_TABLEGEN=${STAGING_BINDIR_NATIVE}/${LLVM_DIR}/tblgen \
    -DLLVM_TARGETS_TO_BUILD="${LLVM_EXTRA_ARCH}${LLVM_ARCH}" \
    -DCMAKE_LINKER:FILEPATH=${LD} \
    -DCMAKE_AR:FILEPATH=${AR} \
    -DCMAKE_OBJCOPY:FILEPATH=${OBJCOPY} \
    -DCMAKE_OBJDUMP:FILEPATH=${OBJDUMP} \
    -DCMAKE_RANLIB:FILEPATH=${RANLIB} \
    -DCMAKE_STRIP:FILEPATH=${STRIP} \
    -DNM_PATH:FILEPATH=${NM} \
    -DLLVM_ENABLE_PIC:BOOL=ON \
    -DLLVM_TARGET_ARCH:STRING=${LLVM_ARCH} \
    -DLLVM_ENABLE_ASSERTIONS:BOOL=ON \
    -DCMAKE_BUILD_TYPE:STRING=RelWithDebInfo \
    -DBUILD_SHARED_LIBS:BOOL=ON \
    -DCMAKE_SKIP_BUILD_RPATH:BOOL=ON \
"

# We need to reset this to avoid breakage as we build out of tree
TOOLCHAIN_OPTIONS = ""

# the difference to the non-native build is that we do not need
# to declare the location of the tblgen executable.
EXTRA_OECMAKE_class-native = "\
    -DLLVM_TARGETS_TO_BUILD=${LLVM_ARCH} \
    -DCMAKE_LINKER:FILEPATH=${LD} \
    -DCMAKE_AR:FILEPATH=${AR} \
    -DCMAKE_OBJCOPY:FILEPATH=${OBJCOPY} \
    -DCMAKE_OBJDUMP:FILEPATH=${OBJDUMP} \
    -DCMAKE_RANLIB:FILEPATH=${RANLIB} \
    -DCMAKE_STRIP:FILEPATH=${STRIP} \
    -DNM_PATH:FILEPATH=${NM} \
"

base_do_compile_prepend() {
    # Avoid *** No rule to make target `native/bin/tblgen', needed by `include/llvm/Intrinsics.gen.tmp'
    oe_runmake tblgen
}

do_install() {
    # Install into a private directory to be able to reorganize the files.
    cd ${LLVM_BUILD_DIR}
    oe_runmake DESTDIR=${LLVM_INSTALL_DIR} install

    # Create our custom target directories
    install -d ${D}${bindir}/${LLVM_DIR}
    install -d ${D}${includedir}/${LLVM_DIR}
    install -d ${D}${libdir}/${LLVM_DIR}

    # Move headers into their own directory
    cp -R ${LLVM_INSTALL_DIR}/${prefix}/include/llvm \
    ${D}${includedir}/${LLVM_DIR}/
    cp -R ${LLVM_INSTALL_DIR}/${prefix}/include/llvm-c \
    ${D}${includedir}/${LLVM_DIR}/

    find ${LLVM_INSTALL_DIR}/${prefix}/lib -name "*" -maxdepth 1 -exec \
    install {} ${D}${libdir}/${LLVM_DIR} \;

    # I dont know another way out. Binaries are installed into a special subdir
    find ${LLVM_INSTALL_DIR}/${prefix}/bin -name "*" -maxdepth 1 -exec \
    install {} ${D}${bindir}/${LLVM_DIR} \;

    # LLVM does not install this by default.
    install bin/tblgen ${D}${bindir}/${LLVM_DIR}
}

SYSROOT_PREPROCESS_FUNCS_append_class-target = " llvm_sysroot_preprocess"

llvm_sysroot_preprocess() {
    cd ${LLVM_BUILD_DIR}

    # Fix the paths in the config script to make it find the binaries and
    # library files. Doing so allows 3rd party configure scripts working
    # unmodified.
    sed -e's!my.*ABS_RUN_DIR =.*!my $ABS_RUN_DIR = "${STAGING_DIR_TARGET}";!' \
        -e's!my.*INCLUDEDIR =.*!my $INCLUDEDIR = "${STAGING_INCDIR}/${LLVM_DIR}";!' \
        -e's!my.*LIBDIR.*!my $LIBDIR = "${STAGING_LIBDIR}/${LLVM_DIR}";!' \
        -e's!my.*BINDIR.*!my $BINDIR = "${STAGING_BINDIR}/${LLVM_DIR}";!' \
        bin/llvm-config > bin/llvm-config${LLVM_RELEASE}

        install -d ${SYSROOT_DESTDIR}${bindir_crossscripts}
        install -m 0755 bin/llvm-config${LLVM_RELEASE} ${SYSROOT_DESTDIR}${bindir_crossscripts}
}

do_install_class-native() {
    # Install into a private directory to be able to reorganize the files.
    cd ${LLVM_BUILD_DIR}
    oe_runmake DESTDIR=${LLVM_INSTALL_DIR} install

    # Create our custom target directories
    install -d ${D}${bindir}/${LLVM_DIR}
    install -d ${D}${includedir}/${LLVM_DIR}
    install -d ${D}${libdir}/${LLVM_DIR}

    # Move headers into their own directory
    cp -R ${LLVM_INSTALL_DIR}/${prefix}/include/llvm \
          ${D}${includedir}/${LLVM_DIR}/
    cp -R ${LLVM_INSTALL_DIR}/${prefix}/include/llvm-c \
          ${D}${includedir}/${LLVM_DIR}/

    find ${LLVM_INSTALL_DIR}/${prefix}/lib -name "*" -maxdepth 1 -exec \
        install {} ${D}${libdir}/${LLVM_DIR} \;

    # I dont know another way out. Binaries are installed into a special subdir
    find ${LLVM_INSTALL_DIR}/${prefix}/bin -name "*" -maxdepth 1 -exec \
        install {} ${D}${bindir}/${LLVM_DIR} \;

    # LLVM does not install this by default.
    install bin/tblgen ${D}${bindir}/${LLVM_DIR}

    # Fix the paths in the config script to make it find the binaries and
    # library files. Doing so allows 3rd party configure scripts working
    # unmodified.
    sed -e's!my.*ABS_RUN_DIR =.*!my $ABS_RUN_DIR = "${STAGING_DIR_TARGET}";!' \
        -e's!my.*INCLUDEDIR =.*!my $INCLUDEDIR = "${STAGING_INCDIR}/${LLVM_DIR}";!' \
        -e's!my.*LIBDIR.*!my $LIBDIR = "${STAGING_LIBDIR}/${LLVM_DIR}";!' \
        -e's!my.*BINDIR.*!my $BINDIR = "${STAGING_BINDIR}/${LLVM_DIR}";!' \
        bin/llvm-config > bin/llvm-config${LLVM_RELEASE}

    install -d ${D}${bindir}
    install -m 0755 bin/llvm-config${LLVM_RELEASE} ${D}${bindir}
}

# Retrieve the target in a way that is compatible to the arch
# value in llvm (>= 2.5)
def get_llvm_arch(d):
    import bb;

    arch = bb.data.getVar('TARGET_ARCH', d, 1)
    if arch == "x86_64" or arch == "i486" or arch == "i586" or arch == "i686":
        arch = "X86"
    elif arch == "arm":
        arch = "ARM"
    elif arch == "mipsel" or arch == "mips":
        arch = "mips"
    elif arch == "powerpc" or arch == "powerpc64":
        arch = "PowerPC"
    else:
        bb.warn("%s does not support %s yet" % (bb.data.getVar('PN', d, 1), arch) );

    return arch
