SUMMARY = "Program for providing universal TLS/SSL tunneling service"
DESCRIPTION = "SSL encryption wrapper between remote client and local (inetd-startable) or remote server."
SECTION = "net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=866cdc7459d91e092b174388fab8d283"
DEPENDS = "openssl zlib tcp-wrappers"

RDEPENDS_${PN} += "perl"

SRC_URI = "ftp://ftp.stunnel.org/stunnel/archive/5.x/${BP}.tar.gz"

SRC_URI[md5sum] = "3d0a932ea650c0fa0da1e45547afdbb8"
SRC_URI[sha256sum] = "2aef568b1955f5e233f6a8e17ebce3d30755f1be44c813f5a48e621f785596e3"

inherit autotools

EXTRA_OECONF += "--with-ssl='${STAGING_EXECPREFIXDIR}' --disable-fips"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES','systemd','systemd','',d)}"
PACKAGECONFIG[systemd] = "--enable-systemd,--disable-systemd,systemd"
