# LLVM does not provide ABI stability between different versions. For this
# reason OE makes it possible to build and install different llvm versions
# at the same time.
#
# This is true for the normal recipes as well as the native ones.
#
# All regular installation directories are prefixed with 'llvm${LLVM_RELEASE}'
# e.g. "${STAGING_BINDIR}/llvm2.5" or "${STAGING_INCDIR}/llvm2.5"
#
# For your program or library that makes use of llvm you do should not need to
# modify anything as long as it uses the results of various llvm-config
# invocations. If you need customizations something is wrong and it needs to be
# fixed (report bug).
#
# However the *recipe* for your program/library *must* declare
# export WANT_LLVM_RELEASE = "<valid version number>"
# The version number is picked up by a generic wrapper script which just calls
# the variant of the specified version.

DESCRIPTION = "The Low Level Virtual Machine"
HOMEPAGE = "http://llvm.org"
# 3-clause BSD-like
LICENSE = "NCSA"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=0ac5f799a2d89477c75b0a378b221855"

DEPENDS = "llvm${LLVM_RELEASE}-native llvm-common"
DEPENDS_virtclass-native = "llvm-common-native cmake-native"

INC_PR = "r2"

SRC_URI = "http://llvm.org/releases/${PV}/llvm-${PV}.tgz"

S = "${WORKDIR}/llvm-${PV}"

inherit cmake perlnative pythonnative

# Defines the LLVM supported arches. By now we always build either for ${BUILD}
# (native) or ${TARGET}. In the future it may make sense to enable all backends
# for the non-native build. The decision which backends are used is made by
# the 3rd party program or library that uses llvm anyway.
LLVM_ARCH = "${@get_llvm_arch(d)}"

# This is used for generating the install directory for the llvm libraries,
# binaries and headers. It makes side by side installation of those possible.
LLVM_RELEASE = "${PV}"

# llvm *must* be built out of tree
OECMAKE_SOURCEPATH = ".."
OECMAKE_BUILDPATH = "build"
EXTRA_OECMAKE = "\
    -DLLVM_TABLEGEN=${STAGING_BINDIR_NATIVE}/llvm${LLVM_RELEASE}/tblgen \
    -DLLVM_TARGETS_TO_BUILD=${LLVM_ARCH} \
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
"
# We need to reset this to avoid breakage as we build out of tree
TOOLCHAIN_OPTIONS = ""

PACKAGES_DYNAMIC = "llvm-*"

# the difference to the non-native build is that we do not need
# to declare the location of the tblgen executable.
EXTRA_OECMAKE_virtclass-native = "\
    -DLLVM_TARGETS_TO_BUILD=${LLVM_ARCH} \
    -DCMAKE_LINKER:FILEPATH=${LD} \
    -DCMAKE_AR:FILEPATH=${AR} \
    -DCMAKE_OBJCOPY:FILEPATH=${OBJCOPY} \
    -DCMAKE_OBJDUMP:FILEPATH=${OBJDUMP} \
    -DCMAKE_RANLIB:FILEPATH=${RANLIB} \
    -DCMAKE_STRIP:FILEPATH=${STRIP} \
    -DNM_PATH:FILEPATH=${NM} \
"

PACKAGES_virtclass-native = ""

PACKAGES_DYNAMIC_virtclass-native = ""

python populate_packages_prepend () {
    libllvm_libdir = bb.data.expand('${libdir}/llvm${LLVM_RELEASE}', d)
    do_split_packages(d, libllvm_libdir, '^lib(.*)\.so$', 'libllvm-%s', 'Split package for %s', allow_dirs=True)
}

FILES_${PN} = ""
ALLOW_EMPTY_${PN} = "1"

FILES_${PN}-dbg += "${libdir}/llvm${LLVM_RELEASE}/.debug ${bindir}/llvm${LLVM_RELEASE}/.debug"

FILES_${PN}-dev = " \
                   ${includedir} \
                   ${bindir}/* \
                   ${libdir}/llvm${LLVM_RELEASE}/LLVMHello.so \
                   ${libdir}/llvm${LLVM_RELEASE}/BugpointPasses.so \
                   ${libdir}/llvm${LLVM_RELEASE}/*.a \
"

do_install() {
    # Install into a private directory to be able to reorganize the files.

    cd ${OECMAKE_BUILDPATH}

    oe_runmake DESTDIR=${WORKDIR}/llvm-install install

    # Create our custom target directories
    install -d ${D}${bindir}/llvm${LLVM_RELEASE}
    install -d ${D}${includedir}/llvm${LLVM_RELEASE}
    install -d ${D}${libdir}/llvm${LLVM_RELEASE}

    #    Move headers into their own directory
    cp -R ${WORKDIR}/llvm-install/${prefix}/include/llvm \
    ${D}${includedir}/llvm${LLVM_RELEASE}/
    cp -R ${WORKDIR}/llvm-install/${prefix}/include/llvm-c \
    ${D}${includedir}/llvm${LLVM_RELEASE}/

    find ${WORKDIR}/llvm-install/${prefix}/lib -name "*" -maxdepth 1 -exec \
    install {} ${D}${libdir}/llvm${LLVM_RELEASE} \;

    #    I dont know another way out. Binaries are installed into a special subdir
    find ${WORKDIR}/llvm-install/${prefix}/bin -name "*" -maxdepth 1 -exec \
    install {} ${D}${bindir}/llvm${LLVM_RELEASE} \;

    # LLVM does not install this by default.
    install bin/tblgen ${D}${bindir}/llvm${LLVM_RELEASE}

        # we install it here unmodified for native and none native and overwrite it
        # later for native case
        install -d ${D}${bindir}
        install -m 0755 bin/llvm-config ${D}${bindir}

    # Fix the paths in the config script to make it find the binaries and
    # library files. Doing so allows 3rd party configure scripts working
    # unmodified.
    sed -e's!my.*ABS_RUN_DIR =.*!my $ABS_RUN_DIR = "${STAGING_DIR_TARGET}";!' \
        -e's!my.*INCLUDEDIR =.*!my $INCLUDEDIR = "${STAGING_INCDIR}/llvm${LLVM_RELEASE}";!' \
        -e's!my.*LIBDIR.*!my $LIBDIR = "${STAGING_LIBDIR}/llvm${LLVM_RELEASE}";!' \
        -e's!my.*BINDIR.*!my $BINDIR = "${STAGING_BINDIR}/llvm${LLVM_RELEASE}";!' \
        bin/llvm-config > bin/llvm-config${LLVM_RELEASE}

        install -d ${SYSROOT_DESTDIR}${bindir_crossscripts}
        install -m 0755 bin/llvm-config${LLVM_RELEASE} ${SYSROOT_DESTDIR}${bindir_crossscripts}
}

do_install_virtclass-native() {
    # Install into a private directory to be able to reorganize the files.

        cd ${OECMAKE_BUILDPATH}

        oe_runmake DESTDIR=${WORKDIR}/llvm-install install

    # Create our custom target directories
    install -d ${D}${bindir}/llvm${LLVM_RELEASE}
    install -d ${D}${includedir}/llvm${LLVM_RELEASE}
    install -d ${D}${libdir}/llvm${LLVM_RELEASE}

    #    Move headers into their own directory
    cp -R ${WORKDIR}/llvm-install/${prefix}/include/llvm \
    ${D}${includedir}/llvm${LLVM_RELEASE}/
    cp -R ${WORKDIR}/llvm-install/${prefix}/include/llvm-c \
    ${D}${includedir}/llvm${LLVM_RELEASE}/

    find ${WORKDIR}/llvm-install/${prefix}/lib -name "*" -maxdepth 1 -exec \
    install {} ${D}${libdir}/llvm${LLVM_RELEASE} \;

    #    I dont know another way out. Binaries are installed into a special subdir
    find ${WORKDIR}/llvm-install/${prefix}/bin -name "*" -maxdepth 1 -exec \
    install {} ${D}${bindir}/llvm${LLVM_RELEASE} \;

    # LLVM does not install this by default.
    install bin/tblgen ${D}${bindir}/llvm${LLVM_RELEASE}

    # Fix the paths in the config script to make it find the binaries and
    # library files. Doing so allows 3rd party configure scripts working
    # unmodified.
    sed -e's!my.*ABS_RUN_DIR =.*!my $ABS_RUN_DIR = "${STAGING_DIR_TARGET}";!' \
        -e's!my.*INCLUDEDIR =.*!my $INCLUDEDIR = "${STAGING_INCDIR}/llvm${LLVM_RELEASE}";!' \
        -e's!my.*LIBDIR.*!my $LIBDIR = "${STAGING_LIBDIR}/llvm${LLVM_RELEASE}";!' \
        -e's!my.*BINDIR.*!my $BINDIR = "${STAGING_BINDIR}/llvm${LLVM_RELEASE}";!' \
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

BBCLASSEXTEND = "native"

