DESCRIPTION = "strongSwan is an OpenSource IPsec implementation for the \
Linux operating system."
HOMEPAGE = "http://www.strongswan.org"
SECTION = "console/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "gmp openssl flex-native flex bison-native"
PR = "r5"

SRC_URI = "http://download.strongswan.org/strongswan-${PV}.tar.bz2"
SRC_URI[md5sum] = "c8b861305def7c0abae04f7bbefec212"
SRC_URI[sha256sum] = "efc13c86e715b5e596d9d8535640c830f83e977fe521afd2c70d68926c4b573e"

EXTRA_OECONF = "--disable-curl --disable-soup --disable-ldap \
        --enable-gmp --disable-mysql --disable-sqlite \
        --enable-openssl"

EXTRA_OECONF += "${@base_contains('DISTRO_FEATURES', 'systemd', '--with-systemdsystemunitdir=${systemd_unitdir}/system/', '--without-systemdsystemunitdir', d)}"

inherit autotools systemd

RRECOMMENDS_${PN} = "kernel-module-ipsec"

PACKAGES += "${PN}-plugins"
FILES_${PN} += "${libdir}/ipsec/lib*${SOLIBS}"
FILES_${PN}-dev += "${libdir}/ipsec/lib*${SOLIBSDEV} ${libdir}/ipsec/*.la"
FILES_${PN}-staticdev += "${libdir}/ipsec/*.a"
FILES_${PN}-dbg += "${libdir}/ipsec/.debug ${libdir}/ipsec/plugins/.debug ${libexecdir}/ipsec/.debug"
FILES_${PN}-plugins += "${libdir}/ipsec/plugins/*"

INSANE_SKIP_${PN}-plugins = "staticdev"

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "${PN}.service"
