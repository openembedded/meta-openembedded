require linux-kexecboot.inc

PR = "${INC_PR}.3"
S = "${WORKDIR}/linux-${PV}"

SRC_URI += "${KERNELORG_MIRROR}/linux/kernel/v3.x/linux-${PV}.tar.bz2;name=kernel \
           ${KERNELORG_MIRROR}/linux/kernel/v3.x/patch-${PV}.8.bz2;apply=yes;name=stablepatch \
           "

SRC_URI[kernel.md5sum] = "7ceb61f87c097fc17509844b71268935"
SRC_URI[kernel.sha256sum] = "c881fc2b53cf0da7ca4538aa44623a7de043a41f76fd5d0f51a31f6ed699d463"
SRC_URI[stablepatch.md5sum] = "202cb750babdda078d6cc2816ab353b1"
SRC_URI[stablepatch.sha256sum] = "1853d2bd4222e554fb4c2c5d818b953be9e3c658636b58a0a5b3860d23312cda"
