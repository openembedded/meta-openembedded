SUMMARY = "ATA over Ethernet Tools"
DESCRIPTION = " \
The aoetools are programs for users of the ATA over Ethernet (AoE)network \
storage protocol, a simple protocol for using storage over anethernet LAN. \
The vblade program (storage target) exports a blockdevice using AoE. \
"
HOMEPAGE = "http://sourceforge.net/projects/${BPN}"
SECTION = "console/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
RRECOMMENDS_${PN} = "kernel-module-aoe"
PR = "r0"


SRC_URI = "http://sourceforge.net/projects/${BPN}/files/${BPN}/${PV}/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "cde7f7cedc7cd9938a5ebfd009dd3ec0"
SRC_URI[sha256sum] = "fd6c57244e9458d7cbe7a0171295ddee4d8cdcae291e40d6acbc641df7296d85"

# EXTRA_OEMAKE is typically: -e MAKEFLAGS=
# the -e causes problems as CFLAGS is modified in the Makefile.
EXTRA_OEMAKE = ""

do_install() {
    oe_runmake DESTDIR=${D} install
}
