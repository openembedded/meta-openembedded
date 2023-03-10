DESCRIPTION = "Different utilities from Android"
SECTION = "console/utils"
LICENSE = "Apache-2.0 & GPL-2.0-only & BSD-2-Clause & BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10 \
    file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6 \
    file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=cb641bc04cda31daea161b1bc15da69f \
    file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9 \
"
DEPENDS = "libbsd libpcre zlib libcap libusb squashfs-tools p7zip libselinux"

ANDROID_MIRROR = "android.googlesource.com"

# matches with 10.0.0+r36
SRCREV_boringssl = "ae2dd49c7cb74d04bdba7c1c9bd62c1e9cdf98f6"
SRCREV_core = "5aa13b053182b758d7a19db0c83e1b9b5bf1ec2e"
SRCREV_extras = "d31740f9d0399f8b938e88e58843d966e1cccab6"
SRCREV_libhardware = "c6925520342a7d37758f85eb1cf3baa20a7b7a18"
SRCREV_build = "28768b3120f751583a2743101b892f210d4715cf"
SRCREV_libunwind = "03a963ecf6ea836b38b3537cbcda0ecfd7a77393"

SRC_URI = " \
    git://salsa.debian.org/android-tools-team/android-platform-external-boringssl;name=boringssl;protocol=https;nobranch=1;destsuffix=git/external/boringssl \
    git://salsa.debian.org/android-tools-team/android-platform-system-core;name=core;protocol=https;nobranch=1;destsuffix=git/system/core \
    git://salsa.debian.org/android-tools-team/android-platform-system-extras;name=extras;protocol=https;nobranch=1;destsuffix=git/system/extras \
    git://${ANDROID_MIRROR}/platform/hardware/libhardware;name=libhardware;protocol=https;nobranch=1;destsuffix=git/hardware/libhardware \
    git://salsa.debian.org/android-tools-team/android-platform-build.git;name=build;protocol=https;nobranch=1;destsuffix=git/build \
    git://salsa.debian.org/android-tools-team/android-platform-external-libunwind.git;protocol=https;name=libunwind;nobranch=1;destsuffix=git/external/libunwind \
"

# Patches copied from android-platform-system-core/debian/patches
# and applied in the order defined by the "series" file
SRC_URI += " \
    file://core-debian/move-log-file-to-proper-dir.patch;patchdir=system/core \
    file://core-debian/Added-missing-headers.patch;patchdir=system/core \
    file://core-debian/libusb-header-path.patch;patchdir=system/core \
    file://core-debian/stdatomic.patch;patchdir=system/core \
    file://core-debian/Nonnull.patch;patchdir=system/core \
    file://core-debian/Vector-cast.patch;patchdir=system/core \
    file://core-debian/use-Python-3-for-mkbootimg.patch;patchdir=system/core \
    file://core-debian/throw-exception-on-unknown-os.patch;patchdir=system/core \
    file://core-debian/simg_dump-python3.patch;patchdir=system/core \
    file://core-debian/fix-attribute-issue-with-gcc.patch;patchdir=system/core \
    file://core-debian/workaround-error-expected-primary-expression-before-.-token.patch;patchdir=system/core \
    file://core-debian/fix-gettid-exception-declaration.patch;patchdir=system/core \
    file://core-debian/fix-build-on-non-x86.patch;patchdir=system/core \
    file://core-debian/add-missing-headers.patch;patchdir=system/core \
    file://core-debian/hard-code-build-number.patch;patchdir=system/core \
    file://core-debian/fix-gcc-11-ftbfs.patch;patchdir=system/core \
    file://core-debian/fix-gnu-hurd.patch;patchdir=system/core \
"

# Patches copied from android-platform-external-libunwind/debian/patches
# and applied in the order defined by the "series" file
SRC_URI += " \
    file://libunwind-debian/user_pt_regs.patch;patchdir=external/libunwind \
    file://libunwind-debian/legacy_built-in_sync_functions.patch;patchdir=external/libunwind \
    file://libunwind-debian/20150704-CVE-2015-3239_dwarf_i.h.patch;patchdir=external/libunwind \
"

# meta-clang specific patches + files:
SRC_URI += " \
    file://core/0001-patching-adb.mk-to-build-in-yocto-environment.patch;patchdir=system/core \
    file://core/0002-libadb.mk-modifications-to-make-it-build-in-yocto-en.patch;patchdir=system/core \
    file://core/0003-socket.h-removing-dependency-of-gtest.patch;patchdir=system/core \
    file://core/0004-patching-fastboot.mk-to-build-in-yocto-environment.patch;patchdir=system/core \
    file://core/0005-fastboot-don-t-use-sparse_file_import_auto-in-load_b.patch;patchdir=system/core \
    file://core/0006-libbase.mk-modifications-to-make-it-build-in-yocto-e.patch;patchdir=system/core \
    file://core/0007-libcrypto_utils.mk-modifications-to-make-it-build-in.patch;patchdir=system/core \
    file://core/0008-libcutils-modifications-to-make-it-build-in-yocto-en.patch;patchdir=system/core \
    file://core/0009-patching-img2simg.mk-to-build-in-yocto-environment.patch;patchdir=system/core \
    file://core/0010-patching-simg2img.mk-to-build-in-yocto-environment.patch;patchdir=system/core \
    file://core/0011-patching-liblog.mk-to-build-in-yocto-environment.patch;patchdir=system/core \
    file://core/0012-patching-libsparse.mk-to-build-in-yocto-environment.patch;patchdir=system/core \
    file://core/0013-patching-libziparchive.mk-to-build-in-yocto-environm.patch;patchdir=system/core \
    file://core/0014-patching-libbacktrace.mk-to-build-in-yocto-environme.patch;patchdir=system/core \
    file://core/0015-Use-namespace-std-to-compile-libbacktrace.patch;patchdir=system/core \
    file://core/0016-Adapt-adbd-to-work-with-yocto.patch;patchdir=system/core \
    file://0001-libcrypto.mk-modifications-to-make-it-build-in-yocto.patch;patchdir=external/boringssl \
    file://0001-patching-libundwind-to-build-in-yocto-environment.patch;patchdir=external/libunwind \
    file://0001-libext4_utils.mk-modifications-to-make-it-build-in-y.patch;patchdir=system/extras \
    file://0002-libfec-change-out_dir-in-makefile.patch;patchdir=system/extras \
    file://rules_yocto.mk;subdir=git \
    file://android-tools-adbd.service \
    file://adbd.mk;subdir=git/system/core/debian \
"

S = "${WORKDIR}/git"
B = "${WORKDIR}/${BPN}"

# http://errors.yoctoproject.org/Errors/Details/1debian881/
ARM_INSTRUCTION_SET:armv4 = "arm"
ARM_INSTRUCTION_SET:armv5 = "arm"

COMPATIBLE_HOST:powerpc = "(null)"
COMPATIBLE_HOST:powerpc64 = "(null)"
COMPATIBLE_HOST:powerpc64le = "(null)"

inherit systemd clang

TOOLCHAIN = "clang"
TOOLCHAIN:class-native = "clang"
DEPENDS:append:class-target = "\
    clang-cross-${TARGET_ARCH} \
"
DEPENDS:append:class-native = " clang-native"

SYSTEMD_SERVICE:${PN} = "android-tools-adbd.service"

# Find libbsd headers during native builds
CC:append:class-native = " -I${STAGING_INCDIR}"
CC:append:class-nativesdk = " -I${STAGING_INCDIR}"

PREREQUISITE_core = "libbase libsparse liblog libcutils"
TOOLS_TO_BUILD = "libcrypto_utils libadb libziparchive fastboot adb img2simg simg2img libbacktrace"
TOOLS_TO_BUILD:append:class-target = " adbd"

# Adb needs sys/capability.h, which is not available for native*
TOOLS:class-native = "boringssl fastboot ext4_utils mkbootimg"
TOOLS:class-nativesdk = "boringssl fastboot ext4_utils mkbootimg"

do_compile() {

    case "${HOST_ARCH}" in
      arm)
        export android_arch=linux-arm
        cpu=arm
        deb_host_arch=arm
      ;;
      aarch64)
        export android_arch=linux-arm64
        cpu=arm64
        deb_host_arch=arm64
      ;;
      riscv64)
        export android_arch=linux-riscv64
      ;;
      mips|mipsel)
        export android_arch=linux-mips
        cpu=mips
        deb_host_arch=mips
      ;;
      mips64|mips64el)
        export android_arch=linux-mips64
        cpu=mips64
        deb_host_arch=mips64
      ;;
      powerpc|powerpc64)
        export android_arch=linux-ppc
      ;;
      i586|i686|x86_64)
        export android_arch=linux-x86
        cpu=x86_64
        deb_host_arch=amd64
      ;;
    esac

    export SRCDIR=${S}

    oe_runmake -f ${S}/external/boringssl/debian/libcrypto.mk -C ${S}/external/boringssl
    oe_runmake -f ${S}/external/libunwind/debian/libunwind.mk -C ${S}/external/libunwind CPU=${cpu}

    for tool in ${PREREQUISITE_core}; do
      oe_runmake -f ${S}/system/core/debian/${tool}.mk -C ${S}/system/core
    done

    for i in `find ${S}/system/extras/debian/ -name "*.mk"`; do
        oe_runmake -f $i -C ${S}/system/extras
    done

    for tool in ${TOOLS_TO_BUILD}; do
        if [ "$tool" = "libbacktrace" ]; then
            oe_runmake -f ${S}/system/core/debian/${tool}.mk -C ${S}/system/core DEB_HOST_ARCH=${deb_host_arch}
        else
            oe_runmake -f ${S}/system/core/debian/${tool}.mk -C ${S}/system/core
        fi
    done

}

do_install() {
    if echo ${TOOLS_TO_BUILD} | grep -q "ext4_utils" ; then
        install -D -p -m0755 ${S}/system/core/libsparse/simg_dump.py ${D}${bindir}/simg_dump
    fi

    if echo ${TOOLS_TO_BUILD} | grep -q "adb " ; then
        install -d ${D}${bindir}
        install -m0755 ${S}/debian/out/usr/lib/android/adb/adb ${D}${bindir}
    fi

    if echo ${TOOLS_TO_BUILD} | grep -q "adbd" ; then
        install -d ${D}${bindir}
        install -m0755 ${B}/adbd/adbd ${D}${bindir}
    fi

    # Outside the if statement to avoid errors during do_package
    install -D -p -m0644 ${WORKDIR}/android-tools-adbd.service \
      ${D}${systemd_unitdir}/system/android-tools-adbd.service

    if echo ${TOOLS_TO_BUILD} | grep -q "fastboot" ; then
        install -d ${D}${bindir}
        install -m0755 ${S}/debian/out/usr/bin/fastboot/fastboot ${D}${bindir}
    fi

    install -d  ${D}${libdir}/android/
    install -m0755 ${S}/debian/out/usr/lib/android/*.so.* ${D}${libdir}/android/
    if echo ${TOOLS_TO_BUILD} | grep -q "mkbootimg" ; then
        install -d ${D}${bindir}
        install -m0755 ${B}/mkbootimg/mkbootimg ${D}${bindir}
    fi
}

PACKAGES += "${PN}-fstools"

RDEPENDS:${BPN} = "${BPN}-conf p7zip"

FILES:${PN}-fstools = "\
    ${bindir}/ext2simg \
    ${bindir}/ext4fixup \
    ${bindir}/img2simg \
    ${bindir}/make_ext4fs \
    ${bindir}/simg2img \
    ${bindir}/simg2simg \
    ${bindir}/simg_dump \
    ${bindir}/mkuserimg \
"
FILES:${PN} += "${libdir}/android ${libdir}/android/*"

BBCLASSEXTEND = "native"
