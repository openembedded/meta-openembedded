DESCRIPTION = "strongSwan is an OpenSource IPsec implementation for the \
Linux operating system."
SUMMARY = "strongSwan is an OpenSource IPsec implementation"
HOMEPAGE = "http://www.strongswan.org"
SECTION = "net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "gmp openssl flex-native flex bison-native"

SRC_URI = "http://download.strongswan.org/strongswan-${PV}.tar.bz2 \
        file://fix-funtion-parameter.patch \
"

SRC_URI[md5sum] = "c52d4228231c2025d9c320d0e9990327"
SRC_URI[sha256sum] = "824da31a1ff89ac2500d56705e6f9ce06fe5260f9caaeb1da35ea13a8691d284"

EXTRA_OECONF = "--enable-gmp \
        --enable-openssl \
        --without-lib-prefix \
"

EXTRA_OECONF += "${@base_contains('DISTRO_FEATURES', 'systemd', '--with-systemdsystemunitdir=${systemd_unitdir}/system/', '--without-systemdsystemunitdir', d)}"

PACKAGECONFIG ??= "sqlite3 curl \
        ${@base_contains('DISTRO_FEATURES', 'ldap', 'ldap', '', d)} \
"
PACKAGECONFIG[sqlite3] = "--enable-sqlite,--disable-sqlite,sqlite3,"
PACKAGECONFIG[ldap] = "--enable-ldap,--disable-ldap,openldap,"
PACKAGECONFIG[curl] = "--enable-curl,--disable-curl,curl,"
PACKAGECONFIG[soup] = "--enable-soup,--disable-soup,libsoup-2.4,"
PACKAGECONFIG[mysql] = "--enable-mysql,--disable-mysql,mysql5,"

inherit autotools systemd pkgconfig

RRECOMMENDS_${PN} = "kernel-module-ipsec"

FILES_${PN} += "${libdir}/ipsec/lib*${SOLIBS} ${libdir}/ipsec/plugins/*.so"
FILES_${PN}-dbg += "${libdir}/ipsec/.debug ${libdir}/ipsec/plugins/.debug ${libexecdir}/ipsec/.debug"
FILES_${PN}-dev += "${libdir}/ipsec/lib*${SOLIBSDEV} ${libdir}/ipsec/*.la ${libdir}/ipsec/plugins/*.la"
FILES_${PN}-staticdev += "${libdir}/ipsec/*.a ${libdir}/ipsec/plugins/*.a"

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "${BPN}.service"
