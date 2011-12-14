DESCRIPTION = "A full-featured SSL VPN solution via tun device."
HOMEPAGE = "http://openvpn.sourceforge.net"
SECTION = "console/network"
LICENSE = "GPLv2"
DEPENDS = "lzo openssl"
RRECOMMENDS_${PN} = "kernel-module-tun"

INC_PR = "r2"

SRC_URI = "http://openvpn.net/release/openvpn-${PV}.tar.gz \
	file://openvpn"
S = "${WORKDIR}/openvpn-${PV}"

CFLAGS += "-fno-inline"

inherit autotools

do_install_append() {
	install -d ${D}/${sysconfdir}/init.d
	install -d ${D}/${sysconfdir}/openvpn
	install -m 755 ${WORKDIR}/openvpn ${D}/${sysconfdir}/init.d
}
