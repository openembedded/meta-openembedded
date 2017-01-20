SUMMARY = "Dibbler DHCPv6 client"
DESCRIPTION = "Dibbler is a portable DHCPv6 implementation. It supports stateful as well as stateless autoconfiguration for IPv6."
HOMEPAGE = "http://klub.com.pl/dhcpv6"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7236695bb6d4461c105d685a8b61c4e3"

SRC_URI = "http://klub.com.pl/dhcpv6/${BPN}/${BP}.tar.gz \
        file://dibbler_fix_getSize_crash.patch \
        file://types.patch \
"
SRC_URI[md5sum] = "93357bea3ec35b0c1d11242055361409"
SRC_URI[sha256sum] = "27869877e060c039cbc24a5f6a9dd69006bf67de0ffdf29a645a80aef6e476a1"

PACKAGECONFIG ??= "debug bind-reuse resolvconf dns-update"

PACKAGECONFIG[debug] = "--enable-debug,,,"
PACKAGECONFIG[efence] = "--enable-efence,,,"
PACKAGECONFIG[bind-reuse] = "--enable-bind-reuse,,,"
PACKAGECONFIG[dst-addr-filter] = "--enable-dst-addr-check,,,"
PACKAGECONFIG[resolvconf] = "--enable-resolvconf,,,"
PACKAGECONFIG[dns-update] = "--enable-dns-update,,,"
PACKAGECONFIG[auth] = "--enable-auth,,,"
PACKAGECONFIG[gtest] = "--enable-gtest-static,,,"

inherit autotools

DEPENDS += "flex-native"

CFLAGS += "-D_GNU_SOURCE"

PACKAGES =+ "${PN}-requestor ${PN}-client ${PN}-relay ${PN}-server"

FILES_${PN}-client = "${sbindir}/${PN}-client"
FILES_${PN}-relay = "${sbindir}/${PN}-relay"
FILES_${PN}-requestor = "${sbindir}/${PN}-requestor"
FILES_${PN}-server = "${sbindir}/${PN}-server"
