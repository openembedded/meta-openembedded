require recipes-kernel/linux/linux-yocto-tiny_3.8.bb
SUMMARY = "Yocto tiny kernel embedding a minimalistic kexecboot initramfs"

PACKAGES = ""
PROVIDES = ""

KERNEL_IMAGE_BASE_NAME = "${KERNEL_IMAGETYPE}-yocto-tiny-kexecboot-${PV}-${PR}-${MACHINE}"
KERNEL_IMAGE_SYMLINK_NAME = "${KERNEL_IMAGETYPE}-yocto-tiny-kexecboot-${MACHINE}"

INITRAMFS_IMAGE = "initramfs-kexecboot-klibc-image"
INITRAMFS_TASK = "initramfs-kexecboot-klibc-image:do_rootfs"


do_populate_sysroot() {
    :
}

do_install() {
    :
}

do_package() {
}

do_package_ipk() {
}
