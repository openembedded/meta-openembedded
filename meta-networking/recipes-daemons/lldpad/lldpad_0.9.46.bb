SUMMARY = "Intel LLDP Agent"
DESCRIPTION = "\
This package contains the Linux user space daemon and configuration tool for \
Intel LLDP Agent with Enhanced Ethernet support for the Data Center."
SECTION = "net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8c2bc283e65df398ced5f5b747e78162"
DEPENDS = "libconfig libnl readline"
SRCREV = "48a5f38778b18d6659a672ccb4640f25c6720827"

SRC_URI = "git://github.com/jrfastab/lldpad.git;protocol=http \
           file://0001-get-inline-functions-work-with-both-gnu11-and-gnu89.patch \
"

S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig systemd

do_configure_prepend() {
    sed -i "s:-Werror::g" ${S}/Makefile.am
}

do_install_append () {
    install -m 0755 -d ${D}${systemd_unitdir}
    mv ${D}${prefix}${systemd_unitdir}/* ${D}${systemd_unitdir}/
    rmdir ${D}${prefix}${systemd_unitdir}
}

FILES_${PN} += "${systemd_unitdir}"
