SUMMARY = "Utilities for signing UEFI binaries for use with secure boot"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "\
    file://LICENSE.GPLv3;md5=9eef91148a9b14ec7f9df333daebc746 \
    file://COPYING;md5=a7710ac18adec371b84a9594ed04fd20 \
"

DEPENDS = "binutils openssl gnu-efi util-linux-libuuid sbsigntool-native"

SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/jejb/sbsigntools.git;protocol=https;name=sbsigntools;branch=master \
    git://github.com/rustyrussell/ccan.git;protocol=https;destsuffix=git/lib/ccan.git;name=ccan;branch=master \
    file://0001-configure-Dont-t-check-for-gnu-efi.patch \
    file://0002-docs-Don-t-build-man-pages.patch \
    file://0003-sbsign-add-x-option-to-avoid-overwrite-existing-sign.patch  \
    file://0004-src-Makefile.am-Add-read_write_all.c-to-common_SOURC.patch \
    file://0005-fileio.c-initialize-local-variables-before-use-in-fu.patch \
    file://0006-Makefile.am-do-not-use-Werror.patch \
"

SRC_URI:append:class-target = "\
    file://0007-create-ccan-tree-use-native-tools.patch \
"

SRCREV_sbsigntools  ?= "9cfca9fe7aa7a8e29b92fe33ce8433e212c9a8ba"
SRCREV_ccan         ?= "b1f28e17227f2320d07fe052a8a48942fe17caa5"
SRCREV_FORMAT       =  "sbsigntools_ccan"

S = "${WORKDIR}/git"

COMPATIBLE_HOST = "(x86_64.*|i.86.*|aarch64.*|arm.*|riscv64.*)-linux"
COMPATIBLE_HOST:armv4 = 'null'

inherit autotools-brokensep pkgconfig

def efi_arch(d):
    import re
    arch = d.getVar("TARGET_ARCH")
    if re.match("i[3456789]86", arch):
        return "ia32"
    return arch

HOST_EXTRACFLAGS += "\
    INCLUDES+='-I${S}/lib/ccan.git/ \
              -I${STAGING_INCDIR_NATIVE}/efi \
              -I${STAGING_INCDIR_NATIVE} \
"

EXTRA_OEMAKE += "\
    INCLUDES='-I${S}/lib/ccan.git' \
    EFI_CPPFLAGS='-I${STAGING_INCDIR} -I${STAGING_INCDIR}/efi \
                  -I${STAGING_INCDIR}/efi/${@efi_arch(d)}' \
"

configure_hook() {
    if [ ! -e lib/ccan ]; then

        # Use empty SCOREDIR because 'make scores' is not run.
        # The default setting depends on (non-whitelisted) host tools.
        sed -i -e 's#^\(SCOREDIR=\).*#\1#' lib/ccan.git/Makefile

        TMPDIR=lib lib/ccan.git/tools/create-ccan-tree \
            --build-type=automake lib/ccan \
            talloc read_write_all build_assert array_size endian
    fi

    # Create generatable docs from git
    (
    echo "Authors of sbsigntool:"
    echo
    git log --format='%an' | sort -u | sed 's,^,\t,'
    ) > AUTHORS

    # Generate simple ChangeLog
    git log --date=short --format='%ad %t %an <%ae>%n%n  * %s%n' > ChangeLog
}

do_configure:prepend:class-target() {
    cd ${S}
    cp ${STAGING_DIR_NATIVE}/${bindir_native}/ccan_depends ${S}/lib/ccan.git/tools/ccan_depends
    configure_hook
    cd ${B}
}

do_configure:prepend:class-native() {
    cd ${S}
    configure_hook
    cd ${B}
}

do_compile:append:class-native() {
    make -C ${S}/lib/ccan.git tools/ccan_depends
}

do_install:append:class-native() {
    install -D ${S}/lib/ccan.git/tools/ccan_depends ${D}/${bindir}/ccan_depends
}

BBCLASSEXTEND = "native nativesdk"
