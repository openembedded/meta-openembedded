SUMMARY = "Program for providing universal TLS/SSL tunneling service"
DESCRIPTION = "SSL encryption wrapper between remote client and local (inetd-startable) or remote server."
SECTION = "net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=866cdc7459d91e092b174388fab8d283"
DEPENDS = "openssl zlib tcp-wrappers"

RDEPENDS_${PN} += "perl"

SRC_URI = "ftp://ftp.stunnel.org/stunnel/archive/5.x/${BP}.tar.gz"

SRC_URI[md5sum] = "a0edda805eb7d6ea600a230fb0979ea1"
SRC_URI[sha256sum] = "032bfc1854f8a0b9e452343c36ec6b52c7e0daef0863423c6b13a61a7c92eb23"

inherit autotools

EXTRA_OECONF += "--with-ssl='${STAGING_EXECPREFIXDIR}' --disable-fips"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES','systemd','systemd','',d)}"
PACKAGECONFIG[systemd] = "--enable-systemd,--disable-systemd,systemd"
