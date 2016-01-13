SECTION = "console/network"
SUMMARY = "dhcpcd - a DHCP client"
DESCRIPTION = "dhcpcd runs on your machine and silently configures your computer to work on the attached networks without trouble and mostly without configuration."

HOMEPAGE = "http://roy.marples.name/projects/dhcpcd/"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://dhcpcd.c;endline=26;md5=77c40d671aff804ca91ea99556da8e9b"

SRC_URI = "http://roy.marples.name/downloads/${BPN}/${BPN}-${PV}.tar.xz"

SRC_URI[md5sum] = "f39c5773e7c4bea352d9fb7367c899de"
SRC_URI[sha256sum] = "ab56af9b2e86913c55a965cb0f835e87749df78318564acf90d5d698f413ad35"

inherit autotools

B = "${S}"
EXTRA_OECONF = "--enable-ipv4 --enable-ipv6"

FILES_${PN}-dbg += "${libdir}/dhcpcd/dev/.debug"

RDEPENDS_${PN} = "udev"
