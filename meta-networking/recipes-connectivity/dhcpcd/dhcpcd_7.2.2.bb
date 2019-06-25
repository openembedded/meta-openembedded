SECTION = "console/network"
SUMMARY = "dhcpcd - a DHCP client"
DESCRIPTION = "dhcpcd runs on your machine and silently configures your computer to work on the attached networks without trouble and mostly without configuration."

HOMEPAGE = "http://roy.marples.name/projects/dhcpcd/"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0531457992a97ecebc6975914a874a9d"

SRC_URI = "http://roy.marples.name/downloads/${BPN}/${BPN}-${PV}.tar.xz"
SRC_URI += "file://0001-remove-INCLUDEDIR-to-prevent-build-issues.patch"
SRC_URI[md5sum] = "2f17034432ea10415ee84a97ef131128"
SRC_URI[sha256sum] = "3db7ff18cba9274da1d2176fb3c7cbe23926a8e58d5c8e244ad55c62d38ba09e"

inherit autotools-brokensep

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'ipv6', d)}"

PACKAGECONFIG[udev] = "--with-udev,--without-udev,udev,udev"
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6"

EXTRA_OECONF = "--enable-ipv4"

FILES_${PN}-dbg += "${libdir}/dhcpcd/dev/.debug"
