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

SRC_URI[md5sum] = "09b686c489ff10db670ca60dbed7ff43"
SRC_URI[sha256sum] = "f6ca69b75c68915f9f3a3972274ec68354dfbbcfc0b9fc55c813a0525c351d3c"

inherit autotools pkgconfig
