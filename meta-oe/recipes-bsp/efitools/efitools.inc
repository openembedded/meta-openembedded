SUMMARY = "Tools to support reading and manipulating the UEFI signature database"
DESCRIPTION = "\
From the EFI Tools package in the Linux user-space, it's now possible \
to read and manipulate the UEFI signatures database via the new \
efi-readvar and efi-updatevar commands. Aside from needing efitools \
1.4, the EFIVARFS file-system is also needed, which was only introduced \
in the Linux 3.8 kernel. \
"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=e28f66b16cb46be47b20a4cdfe6e99a1"

SRC_URI = "\
    git://git.kernel.org/pub/scm/linux/kernel/git/jejb/efitools.git;branch=master \
    file://0001-Fix-for-the-cross-compilation.patch \
    file://0002-Make.rules-define-_GNU_SOURCE-for-a-declaration-of-f.patch \
    file://0003-Fix-the-wrong-dependency-for-blacklist.esl.patch \
    file://0004-LockDown-run-system-warm-reset-after-the-key-provisi.patch \
    file://0005-Allow-to-override-tools-for-target-build.patch \
    file://0006-Makefile-remove-help2man-dependency.patch \
    file://0007-Add-static-keyword-for-IsValidVariableHeader.patch \
    file://0008-Dynamically-load-openssl.cnf-for-openssl-1.0.x-1.1.x.patch \
    file://0009-console.c-Fix-compilation-against-latest-usr-include.patch \
    file://0010-LockDown-enable-the-enrollment-for-DBX.patch \
    file://0011-LockDown-show-the-error-message-with-3-sec-timeout.patch \
    file://0012-Makefile-do-not-build-signed-efi-image.patch \
    file://0013-Build-DBX-by-default.patch \
    file://0014-LockDown-disable-the-entrance-into-BIOS-setup-to-re-.patch \
    file://0015-fix-segfault-for-efitools-commands.patch \
    file://0016-Make.rules-Pass-CFLAGS-to-Makefile.patch \
    file://0017-Make.rules-fix-efi-files-for-gnu-efi-3.0.18.patch \
"
SRCREV = "392836a46ce3c92b55dc88a1aebbcfdfc5dcddce"

DEPENDS = "openssl-native sbsigntool-native \
           libfile-slurp-perl-native \
          "

PARALLEL_MAKE = ""

COMPATIBLE_HOST = '(i.86|x86_64|aarch64).*-linux'

S = "${WORKDIR}/git"

inherit perlnative

EXTRA_OEMAKE = "\
    OPENSSL='${STAGING_BINDIR_NATIVE}/openssl' \
    SBSIGN='${STAGING_BINDIR_NATIVE}/sbsign' \
    NM='${NM}' AR='${AR}' \
    OPENSSL_LIB='${STAGING_LIBDIR_NATIVE}' \
    EXTRA_LDFLAGS='${LDFLAGS}' \
    CFLAGS='${CFLAGS}' \
    OBJCOPY='${OBJCOPY}' \
"
EXTRA_OEMAKE:append:x86 = " ARCH=ia32"
EXTRA_OEMAKE:append:x86-64 = " ARCH=x86_64"
EXTRA_OEMAKE:append:aarch64 = " ARCH=aarch64"

EFI_BOOT_PATH = "/boot/efi/EFI/BOOT"

do_compile:prepend() {
    sed -i -e "1s:#!.*:#!/usr/bin/env nativeperl:" xxdi.pl 
}

do_install() {
    oe_runmake install DESTDIR='${D}${base_prefix}'
}

fakeroot python do_sign:class-target() {
    if d.getVar('GRUB_SIGN_VERIFY') != '1':
        return

    image_dir = d.getVar('D')
    efi_boot_path = d.getVar('EFI_BOOT_PATH')
    uks_boot_sign(os.path.join(image_dir + efi_boot_path, 'LockDown.efi'), d)
}
addtask sign after do_install before do_deploy do_package
do_sign[prefuncs] += "${@'check_boot_public_key' if d.getVar('GRUB_SIGN_VERIFY') == '1' else ''}"

fakeroot python do_sign() {
}

FILES:${PN} += "${EFI_BOOT_PATH}"

SSTATE_ALLOW_OVERLAP_FILES += "${DEPLOY_DIR_IMAGE}/LockDown.efi"
