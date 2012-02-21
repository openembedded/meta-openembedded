require initramfs-kexecboot-image.bb

IMAGE_INSTALL = "kexec-klibc kexecboot-klibc"

export IMAGE_BASENAME = "initramfs-kexecboot-klibc-image"
