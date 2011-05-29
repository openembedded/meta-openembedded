SECTION = "console/network"
DEPENDS = "openssl"
DESCRIPTION = "Socat is a relay for bidirectional data \
transfer between two independent data channels."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=18810669f13b87348459e611d31ab760"
PR = "r1"

SRC_URI = "http://www.dest-unreach.org/socat/download/socat-${PV}.tar.bz2 \
           file://compile.patch"
SRC_URI[md5sum] = "9c0c5e83ce665f38d4d3aababad275eb"
SRC_URI[sha256sum] = "f7395b154914bdaa49805603aac2a90fb3d60255f95691d7779ab4680615e167"

EXTRA_OECONF = " --disable-termios "

inherit autotools

do_install_prepend () {
        mkdir -p ${D}${bindir}
	install -d ${D}${bindir} ${D}${mandir}/man1
}
