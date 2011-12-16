DESCRIPTION = "strongSwan is an OpenSource IPsec implementation for the \
Linux operating system."
HOMEPAGE = "http://www.strongswan.org"
SECTION = "console/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"
DEPENDS = "gmp openssl flex-native flex bison-native"

SRC_URI = "http://download.strongswan.org/strongswan-${PV}.tar.bz2"
SRC_URI[md5sum] = "81a4a699c4a1a49b74061dfa47b5a033"
SRC_URI[sha256sum] = "252d7369d94aa2d79e6fad078853b07ca897ea811ab1e1a2b008bcec0d1e758a"

EXTRA_OECONF = "--disable-curl --disable-soup --disable-ldap \
		--enable-gmp --disable-mysql --disable-sqlite \
		--enable-openssl"

inherit autotools

RRECOMMENDS_${PN} = "kernel-module-ipsec"

FILES_${PN}-dbg += "${libexecdir}/ipsec/.debug ${libexecdir}/ipsec/plugins/.debug"
