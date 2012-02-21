require linux-kexecboot.inc

PR = "${INC_PR}.8"
S = "${WORKDIR}/linux-${PV}"

SRC_URI += "${KERNELORG_MIRROR}/linux/kernel/v3.x/linux-${PV}.tar.bz2;name=kernel \
           ${KERNELORG_MIRROR}/linux/kernel/v3.x/patch-${PV}.10.bz2;apply=yes;name=stablepatch \
           "

SRC_URI[kernel.md5sum] = "8d43453f8159b2332ad410b19d86a931"
SRC_URI[kernel.sha256sum] = "2573d2378c754b0c602b57586e9311e5b38c5d1e6c137f02873833633a4b9359"
SRC_URI[stablepatch.md5sum] = "a8e1c25a93a685ec2a1c3a808715fe9d"
SRC_URI[stablepatch.sha256sum] = "f25126052d1a083a415ddd313b40c7fcdb3742f40474cb4a826af7e43fee29d3"
