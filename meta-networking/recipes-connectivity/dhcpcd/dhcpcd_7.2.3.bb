SECTION = "console/network"
SUMMARY = "dhcpcd - a DHCP client"
DESCRIPTION = "dhcpcd runs on your machine and silently configures your computer to work on the attached networks without trouble and mostly without configuration."

HOMEPAGE = "http://roy.marples.name/projects/dhcpcd/"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0531457992a97ecebc6975914a874a9d"

UPSTREAM_CHECK_URI = "https://roy.marples.name/downloads/dhcpcd/"

SRC_URI = "http://roy.marples.name/downloads/${BPN}/${BPN}-${PV}.tar.xz"
SRC_URI += "file://0001-remove-INCLUDEDIR-to-prevent-build-issues.patch"
SRC_URI[md5sum] = "b7aade2cc47afa6d645f798103365402"
SRC_URI[sha256sum] = "74ab38177548abe968c2abacefffdd97b573acaedd4f77b6c0a54ae38f68566e"

inherit pkgconfig autotools-brokensep

PACKAGECONFIG ?= "udev ${@bb.utils.filter('DISTRO_FEATURES', 'ipv6', d)}"

PACKAGECONFIG[udev] = "--with-udev,--without-udev,udev,udev"
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6"

EXTRA_OECONF = "--enable-ipv4"

FILES_${PN}-dbg += "${libdir}/dhcpcd/dev/.debug"
