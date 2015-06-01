SUMMARY = "Program for providing universal TLS/SSL tunneling service"
DESCRIPTION = "SSL encryption wrapper between remote client and local (inetd-startable) or remote server."
SECTION = "net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=866cdc7459d91e092b174388fab8d283"
DEPENDS = "openssl zlib tcp-wrappers"

RDEPENDS_${PN} += "perl"

SRC_URI = "ftp://ftp.stunnel.org/stunnel/archive/5.x/${BP}.tar.gz"

SRC_URI[md5sum] = "e70f001ee190105c5a10a74f4bd54901"
SRC_URI[sha256sum] = "c3e79e582621a0827125e35e1c00450190104fc02dc3c5274cb02b05859fd472"

inherit autotools

EXTRA_OECONF += "--with-ssl='${STAGING_EXECPREFIXDIR}' --disable-fips"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES','systemd','systemd','',d)}"
PACKAGECONFIG[systemd] = "--enable-systemd,--disable-systemd,systemd"
