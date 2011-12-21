DESCRIPTION = "libnfnetlink is the low-level library for netfilter related \
kernel/userspace communication. It provides a generic messaging \
infrastructure for in-kernel netfilter subsystems (such as nfnetlink_log, \
nfnetlink_queue, nfnetlink_conntrack) and their respective users and/or \
management tools in userspace."
HOMEPAGE = "http://www.netfilter.org/projects/libnfnetlink/index.html"
SECTION = "devel/libs"
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://Makefile.in;endline=13;md5=a44bb8f4c22793d55c5e879eec256702"

SRC_URI = "http://www.netfilter.org/projects/libnfnetlink/files/libnfnetlink-${PV}.tar.bz2;name=libnfnetlink-${PV}"
SRC_URI[libnfnetlink-1.0.0.md5sum] = "016fdec8389242615024c529acc1adb8"
SRC_URI[libnfnetlink-1.0.0.sha256sum] = "3752b03a4c09821ee9a2528d69289423a01e7171f1a22dfdd11d5459e03972fb"

inherit autotools pkgconfig
