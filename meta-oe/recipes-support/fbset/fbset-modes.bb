DESCRIPTION = "Default display timings and resolutions for fbset"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PV = "0.1.0"
PR = "r6"

SRC_URI = "file://fb.modes"

do_install() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/fb.modes ${D}${sysconfdir}
}

inherit allarch
CONFFILES_${PN} = "${sysconfdir}/fb.modes"

