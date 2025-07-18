SUMMARY = "A client for the Cisco3000 VPN Concentrator"
HOMEPAGE = "http://www.unix-ag.uni-kl.de/~massar/vpnc/"
SECTION = "net"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=173b74cb8ac640a9992c03f3bce22a33"

DEPENDS += "libgcrypt"

PV .= "r550-2jnpr1"
SRCREV = "b1243d29e0c00312ead038b04a2cf5e2fa31d740"
SRC_URI = "git://github.com/ndpgroup/vpnc;branch=master;protocol=https \
           file://long-help \
           file://default.conf \
           file://0001-search-for-log-help-in-build-dir.patch \
           file://0002-Fix-vpnc-install-for-cross-compile.patch \
           file://0003-error.h-is-specific-to-glibc-on-linux.patch \
           file://0004-Use-pkgconfig-instead-of-libgcrypt-config.patch \
           file://0005-include-sys-ttydefaults.h-for-CEOT-definition.patch \
           file://0006-sysdep-Add-header-include-sequence-to-adjust-for-mus.patch \
           file://0007-add-error-API-when-error.h-is-not-on-platform.patch \
           file://0008-include-sysdep.h-before-net-if_tun.h.patch \
           file://0009-reduce-lifetime-value.patch \
           "

PACKAGECONFIG ?= "gnutls"

PACKAGECONFIG[gnutls] = ",,gnutls"
PACKAGECONFIG[openssl] = ",,openssl"

PACKAGES =+ "${PN}-script"


inherit perlnative pkgconfig

#EXTRA_OEMAKE = "-e MAKEFLAGS="

do_configure:append () {
    # Make sure we use our nativeperl wrapper
    sed -i "1s:#!.*:#!/usr/bin/env nativeperl:" ${S}/*.pl
    cp ${UNPACKDIR}/long-help ${S}
}

do_install () {
    sed -i s:m600:m\ 600:g Makefile
    oe_runmake 'DESTDIR=${D}' 'PREFIX=/usr' install
    rm -f ${D}${sysconfdir}/vpnc/vpnc.conf #This file is useless
    install ${UNPACKDIR}/default.conf ${D}${sysconfdir}/vpnc/default.conf
}

SYSROOT_PREPROCESS_FUNCS += "vpnc_sysroot_preprocess"

vpnc_sysroot_preprocess () {
    install -d ${SYSROOT_DESTDIR}${sysconfdir}/vpnc
    install -m 755 ${D}${sysconfdir}/vpnc/vpnc-script ${SYSROOT_DESTDIR}${sysconfdir}/vpnc
}

FILES:${PN}-script = "${sysconfdir}/vpnc/vpnc-script"

CONFFILES:${PN} = "${sysconfdir}/vpnc/default.conf"
RDEPENDS:${PN} = "perl-module-io-file ${PN}-script"
RRECOMMENDS:${PN} = "kernel-module-tun"
