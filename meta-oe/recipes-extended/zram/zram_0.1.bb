DESCRIPTION = "Linux zram compressed in-memory swap"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

inherit update-rc.d systemd

RDEPENDS_${PN} = "util-linux-swaponoff kmod"
RRECOMMENDS_${PN} = "kernel-module-zram"

PR = "r3"

SRC_URI = " \
           file://init \
           file://zram.service \
"

do_install () {
    # Sysvinit
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/zram

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/zram.service ${D}${systemd_unitdir}/system
}

FILES_${PN} = "${sysconfdir}/init.d"
INITSCRIPT_NAME = "zram"
INITSCRIPT_PARAMS = "start 05 2 3 4 5 . stop 22 0 1 6 ."

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "zram.service"
