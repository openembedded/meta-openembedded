SUMMARY = "OpenZFS on Linux and FreeBSD"
DESCRIPTION = "OpenZFS on Linux and FreeBSD"
LICENSE = "CDDL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7087caaf1dc8a2856585619f4a787faa"
HOMEPAGE = "https://github.com/openzfs/zfs"

SRCREV = "079ba86d71571bf997ff688da061d8c4aa2fd052"
SRC_URI = "git://github.com/openzfs/zfs;protocol=https;branch=zfs-2.2-release;tag=${BP} \
           file://0001-Define-strndupa-if-it-does-not-exist.patch \
           file://0002-fixes-broken-aarch64-inline-assembly-for-gcc-13.1.patch \
           file://0003-fs-tests-cmd-readmmap-Replace-uint_t-with-uint32_t.patch \
           file://0004-linux-use-sys-stat.h-instead-of-linux-stat.h.patch \
"


# Using both 'module' and 'autotools' classes seems a bit odd, they both
# define a do_compile function.
# That's why we opt for module-base, also this prevents module splitting.
inherit module-base pkgconfig autotools bash-completion

DEPENDS = "virtual/kernel zlib util-linux libtirpc openssl curl"

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd sysvinit', d)} \
                  ${@bb.utils.filter('DISTRO_FEATURES', 'pam', d)} \
                 "

PACKAGECONFIG[pam] = "--enable-pam --with-pamconfigsdir=${datadir}/pam-configs --with-pammoduledir=${base_libdir}/security, --disable-pam"
PACKAGECONFIG[systemd] = "--enable-systemd,--disable-systemd,"
PACKAGECONFIG[sysvinit] = "--enable-sysvinit,--disable-sysvinit,"

EXTRA_OECONF:append = " \
    --disable-pyzfs \
    --with-linux=${STAGING_KERNEL_DIR} --with-linux-obj=${STAGING_KERNEL_BUILDDIR} \
    --with-mounthelperdir=${base_sbin} \
    --with-udevdir=${base_libdir}/udev \
    --with-systemdunitdir=${systemd_system_unitdir} \
    --with-systemdgeneratordir=${nonarch_base_libdir}/systemd/system-generators \
    --with-systemdpresetdir=${nonarch_base_libdir}/systemd/system-preset \
    --with-systemdmodulesloaddir=${sysconfdir}/module-load.d \
    --without-dracutdir --enable-linux-builtin \
"

# Reproducibility: Force target distribution ("vendor") to Debian to match
# default values for things like: NFS server service name, bash completion
# path, configuration files, ...
# The Debian values do match the OpenEmbedded ones.
EXTRA_OECONF:append = " --with-vendor=debian"

EXTRA_OEMAKE:append = " \
    INSTALL_MOD_PATH=${D}${root_prefix} \
"

do_install:append() {
    # /usr/share/zfs contains the zfs-tests folder which we do not need:
    rm -rf ${D}${datadir}/zfs

    rm -rf ${D}${datadir}/initramfs-tools
}

FILES:${PN} += "\
    ${nonarch_base_libdir}/modules \
    ${systemd_system_unitdir} \
    ${nonarch_base_libdir}/systemd/system-generators \
    ${nonarch_base_libdir}/systemd/system-preset \
    ${sysconfdir}/modules-load.d/${BPN}.conf \
    ${sysconfdir}/default/${BPN} \
    ${sysconfdir}/sudoers.d/${BPN} \
    ${sysconfdir}/${BPN} \
    ${base_libdir}/udev \
    ${sbindir} \
    ${bindir} \
    ${libexecdir}/${BPN} \
    ${libdir} \
    ${datadir}/pam-configs \
    ${base_libdir}/security \
"

FILES:${PN}-dev += "\
    ${prefix}/src/zfs-${PV} \
    ${prefix}/src/spl-${PV} \
"
# Not yet ported to rv32
COMPATIBLE_HOST:riscv32 = "null"
# conflicting definition of ABS macro from asm/asm.h from kernel
COMPATIBLE_HOST:mips = "null"
# FTBFS on aarch64 with 6.2+ kernel see https://github.com/openzfs/zfs/issues/14555
COMPATIBLE_HOST:aarch64 = "null"
