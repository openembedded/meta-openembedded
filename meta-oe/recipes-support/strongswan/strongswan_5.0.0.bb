DESCRIPTION = "strongSwan is an OpenSource IPsec implementation for the \
Linux operating system."
HOMEPAGE = "http://www.strongswan.org"
SECTION = "console/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "gmp openssl flex-native flex bison-native"

SRC_URI = "http://download.strongswan.org/strongswan-${PV}.tar.bz2"
SRC_URI[md5sum] = "c8b861305def7c0abae04f7bbefec212"
SRC_URI[sha256sum] = "efc13c86e715b5e596d9d8535640c830f83e977fe521afd2c70d68926c4b573e"

EXTRA_OECONF = "--disable-curl --disable-soup --disable-ldap \
		--enable-gmp --disable-mysql --disable-sqlite \
		--enable-openssl"

inherit autotools

RRECOMMENDS_${PN} = "kernel-module-ipsec"

FILES_${PN}-dbg += "${libexecdir}/ipsec/.debug ${libexecdir}/ipsec/plugins/.debug"
