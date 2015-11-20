DESCRIPTION = "Network Block Device"
HOMEPAGE = "http://nbd.sourceforge.net"
SECTION = "net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://depcomp;beginline=6;endline=26;md5=3a3627e6495e2ff2a3316bcdaad91e9c"

DEPENDS = "glib-2.0"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "73d11644a28b9f335292cdb3bdc4b74b"
SRC_URI[sha256sum] = "14420f74cb16dc609a9302ed1efd653064bed7a8357e9d73daabc33608e3f2a0"

inherit autotools pkgconfig

PACKAGES = "${PN}-client ${PN}-server ${PN}-dbg ${PN}-trdump ${PN}-doc"

FILES_${PN}-client = "${sbindir}/${BPN}-client"
FILES_${PN}-server = "${bindir}/${BPN}-server"
FILES_${PN}-trdump = "${bindir}/${BPN}-trdump"
