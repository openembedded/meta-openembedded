KLIBC_UTILS_VARIANT ?= "shared"
KLIBC_UTILS_PKGNAME ?= "klibc-utils"

# modprobe and losetup go to ${base_sbindir}
# even though debian packages all in /usr/lib/klibc/bin

do_install_append() {

        install -d ${D}${base_bindir}
        install -d ${D}${base_sbindir}

        # debian packages kinit + kinit.shared but only sh.shared
        if [ "${KLIBC_UTILS_VARIANT}" = "shared" ]; then
            install -m 755 usr/kinit/kinit ${D}${base_bindir}/kinit
            install -m 755 usr/kinit/kinit.shared ${D}${base_bindir}/kinit.shared
            install -m 755 usr/dash/sh.shared ${D}${base_bindir}/sh.shared
        else
            install -m 755 usr/dash/sh ${D}${base_bindir}/sh
            install -m 755 usr/kinit/kinit ${D}${base_bindir}/kinit
        fi

        install -m 755 usr/gzip/gzip ${D}${base_bindir}
        install -m 755 usr/kinit/fstype/${KLIBC_UTILS_VARIANT}/fstype ${D}${base_bindir}
        install -m 755 usr/kinit/ipconfig/${KLIBC_UTILS_VARIANT}/ipconfig ${D}${base_bindir}
        install -m 755 usr/kinit/nfsmount/${KLIBC_UTILS_VARIANT}/nfsmount ${D}${base_bindir}
        install -m 755 usr/kinit/resume/${KLIBC_UTILS_VARIANT}/resume ${D}${base_bindir}
        install -m 755 usr/kinit/run-init/${KLIBC_UTILS_VARIANT}/run-init ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/cat ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/chroot ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/cpio ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/dd ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/dmesg ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/false ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/halt ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/kill ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/ln ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/losetup ${D}${base_sbindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/ls ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/minips ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/mkdir ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/mkfifo ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/mknod ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/mount ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/mv ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/nuke ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/pivot_root ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/poweroff ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/readlink ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/reboot ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/sleep ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/sync ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/true ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/umount ${D}${base_bindir}
        install -m 755 usr/utils/${KLIBC_UTILS_VARIANT}/uname ${D}${base_bindir}
        ln -s gzip ${D}${base_bindir}/gunzip
        ln -s gzip ${D}${base_bindir}/zcat
}

RDEPENDS_klibc-utils-cat = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-chroot = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-cpio = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-dd = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-dmesg = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-false = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-fstype = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-gzip = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-halt = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-ipconfig = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-kill = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-kinit.shared = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-ln = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-losetup = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-ls = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-minips = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-mkdir = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-mkfifo = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-mknod = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-mount = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-mv = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-nfsmount = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-nuke = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-pivot-root = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-poweroff = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-readlink = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-reboot = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-resume = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-run-init = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-sh.shared = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-sleep = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-sync = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-true = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-umount = "${THIS_LIBKLIBC}"
RDEPENDS_klibc-utils-uname = "${THIS_LIBKLIBC}"

PACKAGES_DYNAMIC += "^${KLIBC_UTILS_PKGNAME}-.*"

python populate_packages_prepend () {

    base_bin_dir = bb.data.expand('${base_bindir}', d)
    do_split_packages(d, base_bin_dir, '(.*)', '${KLIBC_UTILS_PKGNAME}-%s', 'Klibc util for %s', extra_depends='', allow_links=True, allow_dirs=True)

    base_sbin_dir = bb.data.expand('${base_sbindir}', d)
    do_split_packages(d, base_sbin_dir, '(.*)', '${KLIBC_UTILS_PKGNAME}-%s', 'Klibc util for %s', extra_depends='', allow_dirs=True)
}
