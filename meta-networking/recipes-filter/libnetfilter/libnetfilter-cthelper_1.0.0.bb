SUMMARY = "Netfilter connection tracking helper library"
DESCRIPTION = "Userspace library providing a programming interface (API) to the Linux kernel netfilter user-space helper infrastructure"
HOMEPAGE = "http://www.netfilter.org/projects/libnetfilter_cthelper/index.html"
SECTION = "libs"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"
DEPENDS = "libmnl"

SRC_URI = "http://www.netfilter.org/projects/libnetfilter_cthelper/files/libnetfilter_cthelper-${PV}.tar.bz2;name=tar \
           file://libnetfilter-cthelper-visibility-hidden.patch \
"
SRC_URI[tar.md5sum] = "b2efab1a3a198a5add448960ba011acd"
SRC_URI[tar.sha256sum] = "07618e71c4d9a6b6b3dc1986540486ee310a9838ba754926c7d14a17d8fccf3d"

S = "${WORKDIR}/libnetfilter_cthelper-${PV}"

inherit autotools pkgconfig
