SUMMARY = "The calibrator and other useful utilities for TI wireless solution based on wl12xx driver"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=4725015cb0be7be389cf06deeae3683d"

DEPENDS = "libnl"

PV = "0.1+gitr${SRCPV}"

SRCREV = "b03d9ce6362e6d22d6929f2736409af3b0fd3c88"
SRC_URI = "git://github.com/TI-OpenLink/ti-utils.git;branch=r5-jb"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = 'CROSS_COMPILE=${TARGET_PREFIX} \
                CC="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}" \
                CFLAGS="${CFLAGS} -I${STAGING_INCDIR}/libnl3 -DCONFIG_LIBNL20" NLVER=3 \
                LDFLAGS="${LDFLAGS} ${TOOLCHAIN_OPTIONS}" \
'

#only install the calibrator utility, firmware is already within linux-firmware
do_install() {
    install -d ${D}${bindir}
    install -m 0755 calibrator ${D}${bindir}/
}
