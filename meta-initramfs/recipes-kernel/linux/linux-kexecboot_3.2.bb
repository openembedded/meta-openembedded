require linux-kexecboot.inc

PR = "${INC_PR}.1"
S = "${WORKDIR}/linux-${PV}"

SRC_URI += "${KERNELORG_MIRROR}/linux/kernel/v3.x/linux-${PV}.tar.bz2;name=kernel \
           ${KERNELORG_MIRROR}/linux/kernel/v3.x/patch-${PV}.1.bz2;apply=yes;name=stablepatch \
           "

SRC_URI[kernel.md5sum] = "7ceb61f87c097fc17509844b71268935"
SRC_URI[kernel.sha256sum] = "c881fc2b53cf0da7ca4538aa44623a7de043a41f76fd5d0f51a31f6ed699d463"
SRC_URI[stablepatch.md5sum] = "31fc34340f11118873463a1d59d47b7f"
SRC_URI[stablepatch.sha256sum] = "e9a26dc5faa309ae5172e3c65081fa98b60befab160fb14d9a75816bbde15626"
