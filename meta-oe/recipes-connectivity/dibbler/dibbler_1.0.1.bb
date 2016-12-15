SUMMARY = "A portable DHCPv6 implementation"
DESCRIPTION = "Dibbler is a portable DHCPv6 implementation. It supports stateful \
(i.e. IPv6 address granting and IPv6 prefix delegation) as well as stateless (i.e. \
option granting) autoconfiguration for IPv6."
HOMEPAGE = "http://klub.com.pl/dhcpv6"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7236695bb6d4461c105d685a8b61c4e3 \
                    file://bison++/COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

DEPENDS = "flex-native bison-native"

SRC_URI = "http://klub.com.pl/dhcpv6/dibbler/dibbler-${PV}.tar.gz"

SRC_URI[md5sum] = "93357bea3ec35b0c1d11242055361409"
SRC_URI[sha256sum] = "27869877e060c039cbc24a5f6a9dd69006bf67de0ffdf29a645a80aef6e476a1"

inherit autotools

PACKAGECONFIG ?= "auth bind-reuse dns-update dst-addr-filter link-state"

PACKAGECONFIG[auth] = "--enable-auth,--disable-auth"
PACKAGECONFIG[bind-reuse] = "--enable-bind-reuse,--disable-bind-reuse"
PACKAGECONFIG[debug] = "--enable-debug,--disable-debug"
PACKAGECONFIG[dns-update] = "--enable-dns-update,--disable-dns-update"
PACKAGECONFIG[dst-addr-filter] = "--enable-dst-addr-filter,--disable-dst-addr-filter"
PACKAGECONFIG[link-state] = "--enable-link-state,--disable-link-state"
PACKAGECONFIG[remote-autoconf] = "--enable-remote-autoconf,--disable-remote-autoconf"
PACKAGECONFIG[resolvconf] = "--enable-resolvconf,--disable-resolvconf"

EXTRA_OECONF += " \
    --disable-efence \
    --with-gtest=no \
"

PACKAGES =+ "${PN}-client ${PN}-relay ${PN}-requestor ${PN}-server"

FILES_${PN}-client = "${sbindir}/${PN}-client"
FILES_${PN}-relay = "${sbindir}/${PN}-relay"
FILES_${PN}-requestor = "${sbindir}/${PN}-requestor"
FILES_${PN}-server = "${sbindir}/${PN}-server"
