SUMMARY = "Library for low-level interaction with nftables Netlink's API over libmnl"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=79808397c3355f163c012616125c9e26"
SECTION = "libs"
DEPENDS = "libmnl"

SRCREV = "09456c720e9c00eecc08e41ac6b7c291b3821ee5"
SRC_URI = "git://git.netfilter.org/libnftnl;branch=master \
           file://0001-avoid-naming-local-function-as-one-of-printf-family.patch \
           "

S = "${WORKDIR}/git"

inherit autotools pkgconfig
