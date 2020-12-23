LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=72d977d697c3c05830fdff00a7448931"
SRCREV = "d50a2001ec994031233ad9c0cc1647fad41835f3"
PV = "1.0+git${SRCPV}"

SRC_URI = "git://github.com/hartkopp/can-isotp.git;branch=4.17+;protocol=https"

S = "${WORKDIR}/git"

inherit module

EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

