DESCRIPTION = "GNU Netcat"
HOMEPAGE = "http://netcat.sourceforge.net"
SECTION = "console/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

PR = "r0"

SRC_URI = "${SOURCEFORGE_MIRROR}/netcat/netcat-${PV}.tar.bz2"

SRC_URI[md5sum] = "0a29eff1736ddb5effd0b1ec1f6fe0ef"
SRC_URI[sha256sum] = "b55af0bbdf5acc02d1eb6ab18da2acd77a400bafd074489003f3df09676332bb"

inherit autotools update-alternatives gettext

do_install_append() {
	mv ${D}${bindir}/nc ${D}${bindir}/nc.${PN}
}

ALTERNATIVE_${PN} = "nc"
ALTERNATIVE_PRIORITY = "100"
