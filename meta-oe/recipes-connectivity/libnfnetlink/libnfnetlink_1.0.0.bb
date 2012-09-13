DESCRIPTION = "libnfnetlink is the low-level library for netfilter related \
kernel/userspace communication. It provides a generic messaging \
infrastructure for in-kernel netfilter subsystems (such as nfnetlink_log, \
nfnetlink_queue, nfnetlink_conntrack) and their respective users and/or \
management tools in userspace."
HOMEPAGE = "http://www.netfilter.org/projects/libnfnetlink/index.html"
SECTION = "devel/libs"
LICENSE = "GPLv2+"

PR = "r1"

LIC_FILES_CHKSUM = "file://src/libnfnetlink.c;beginline=3;endline=11;md5=28bd3bf7f60a78101491eef6b9bb9eba"

SRC_URI = "http://www.netfilter.org/projects/libnfnetlink/files/libnfnetlink-${PV}.tar.bz2;name=libnfnetlink-${PV}"
SRC_URI[libnfnetlink-1.0.0.md5sum] = "016fdec8389242615024c529acc1adb8"
SRC_URI[libnfnetlink-1.0.0.sha256sum] = "3752b03a4c09821ee9a2528d69289423a01e7171f1a22dfdd11d5459e03972fb"

inherit autotools pkgconfig
