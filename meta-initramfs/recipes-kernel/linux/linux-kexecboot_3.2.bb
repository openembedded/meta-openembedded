require linux-kexecboot.inc

PR = "${INC_PR}.2"
S = "${WORKDIR}/linux-${PV}"

SRC_URI += "${KERNELORG_MIRROR}/linux/kernel/v3.x/linux-${PV}.tar.bz2;name=kernel \
           ${KERNELORG_MIRROR}/linux/kernel/v3.x/patch-${PV}.7.bz2;apply=yes;name=stablepatch \
           "

SRC_URI[kernel.md5sum] = "7ceb61f87c097fc17509844b71268935"
SRC_URI[kernel.sha256sum] = "c881fc2b53cf0da7ca4538aa44623a7de043a41f76fd5d0f51a31f6ed699d463"
SRC_URI[stablepatch.md5sum] = "899624bffed6a19578613b672cc9483f"
SRC_URI[stablepatch.sha256sum] = "779c548f4418f0baa782252d15a54fadf3c88d79fc547ee5801d31798020e701"
