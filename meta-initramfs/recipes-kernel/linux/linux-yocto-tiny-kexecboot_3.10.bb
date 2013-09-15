require recipes-kernel/linux/linux-yocto-tiny_3.10.bb
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

do_package_deb() {
    :
}

do_package_ipk() {
    :
}

do_package_rpm() {
    :
}

do_package_tar() {
    :
}

do_package_write_deb() {
    :
}

do_package_write_ipk() {
    :
}

do_package_write_rpm() {
    :
}

do_package_write_tar() {
    :
}

do_packagedata() {
    :
}
