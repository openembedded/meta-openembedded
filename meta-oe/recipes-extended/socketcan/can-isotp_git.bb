LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=72d977d697c3c05830fdff00a7448931"
SRCREV = "${AUTOREV}"
inherit module

PV = "1.0+git${SRCPV}"
SRC_URI = "git://github.com/hartkopp/can-isotp.git;protocol=https"

S = "${WORKDIR}/git"


EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

inherit module

#/linux/can/isotp.h is the include used in most examples, feel free to change 

do_install_append() {
    install -d ${D}${includedir}/linux/can/
    install -m 644 ${S}/include/uapi/linux/can/isotp.h ${D}${includedir}/linux/can/isotp.h
}
