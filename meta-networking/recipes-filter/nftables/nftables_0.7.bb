SUMMARY = "Netfilter Tables userspace utillites"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d1a78fdd879a263a5e0b42d1fc565e79"
SECTION = "net"

DEPENDS = "libmnl libnftnl readline gmp"
RRECOMMENDS_${PN} += "kernel-module-nf-tables \
    "

SRC_URI = "http://www.netfilter.org/projects/nftables/files/${BP}.tar.bz2 \
           file://fix-to-generate-ntf.8.patch \
          "
SRC_URI[md5sum] = "4c005e76a15a029afaba71d7db21d065"
SRC_URI[sha256sum] = "fe639239d801ce5890397f6f4391c58a934bfc27d8b7d5ef922692de5ec4ed43"

ASNEEDED = ""

inherit autotools pkgconfig
