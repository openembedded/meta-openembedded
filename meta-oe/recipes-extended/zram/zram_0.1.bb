DESCRIPTION = "Linux zram compressed in-memory swap"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

inherit allarch update-rc.d

RDEPENDS_${PN} = "util-linux-swaponoff kmod kernel-module-zram"

PR = "r0"

SRC_URI = " \
           file://init \
          "

do_install () {
	# Sysvinit
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/zram
}

FILES_${PN} = "${sysconfdir}/init.d"
INITSCRIPT_NAME = "zram"
INITSCRIPT_PARAMS = "start 05 2 3 4 5 . stop 22 0 1 6 ."
