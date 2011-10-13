DESCRIPTION = "read temperature sensors in a 1-Wire net"
SECTION = "util"
DEPENDS = "libusb1"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=44fee82a1d2ed0676cf35478283e0aa0"

SRC_URI = "http://www.digitemp.com/software/linux/digitemp-${PV}.tar.gz"

inherit autotools

do_install() {
    install -d ${D}${sbindir}
    install digitemp_* ${D}${sbindir}
}
