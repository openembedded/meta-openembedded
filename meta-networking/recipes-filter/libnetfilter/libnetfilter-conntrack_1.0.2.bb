SUMMARY = "Netfilter connection tracking library"
DESCRIPTION = "Userspace library providing a programming interface (API) to the Linux kernel netfilter connection tracking state table"
HOMEPAGE = "http://www.netfilter.org/projects/libnetfilter_conntrack/index.html"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"
DEPENDS = "libnfnetlink libmnl"

SRC_URI = "http://www.netfilter.org/projects/libnetfilter_conntrack/files/libnetfilter_conntrack-${PV}.tar.bz2;name=tar"
SRC_URI[tar.md5sum] = "447114b5d61bb9a9617ead3217c3d3ff"
SRC_URI[tar.sha256sum] = "a0bd747dd58ae1513586b43c7125b41e6325f97eb95ac63d53cf5aeb33254d12"

S = "${WORKDIR}/libnetfilter_conntrack-${PV}"

inherit autotools pkgconfig
