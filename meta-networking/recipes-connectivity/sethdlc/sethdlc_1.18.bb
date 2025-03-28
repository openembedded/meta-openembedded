DESCRIPTION = "set Linux HDLC packet radio modem driver port information"
HOMEPAGE = "https://www.kernel.org/pub/linux/utils/net/hdlc"
SECTION = "comm"
LICENSE = "GPL-2.0-only & GPL-2.0-or-later "
LIC_FILES_CHKSUM = "file://sethdlc.c;endline=10;md5=90f936879e9f8b755a138aeb348782eb"


SRC_URI = "https://www.kernel.org/pub/linux/utils/net/hdlc/${BP}.tar.gz \
           file://sethdlc-fix-host-contamination.patch \
"
SRC_URI[md5sum] = "9016878156a5eadb06c0bae71cc5c9ab"
SRC_URI[sha256sum] = "21b1e2e1cb0e288b0ec8fcfd9fed449914e0f8e6fc273706bd5b3d4f6ab6b04e"


PACKAGE_ARCH = "${MACHINE_ARCH}"
DEPENDS = "virtual/kernel"

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX} CC='${CC} ${LDFLAGS}' \
                KERNEL_DIR=${STAGING_KERNEL_DIR} "

do_compile:prepend () {
    oe_runmake clean
}


do_install() {
    install -d ${D}/${bindir}
    install sethdlc ${D}/${bindir}/
}

FILES:${PN} += "${bindir}/sethdlc"
