SUMMARY = "Library for low-level interaction with nftables Netlink's API over libmnl"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=79808397c3355f163c012616125c9e26"
SECTION = "libs"
DEPENDS = "libmnl"

SRC_URI = "http://netfilter.org/projects/libnftnl/files/${BP}.tar.bz2  \
          "

SRC_URI[md5sum] = "203701a73cc3c51ca751d7cb2e176250"
SRC_URI[sha256sum] = "cf0ae7eab6a6866192a1fbd6b370a8a2cbe2066fe3975ab44939fd50747c27f7"

inherit autotools pkgconfig
