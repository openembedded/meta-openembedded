DESCRIPTION = "Libdivecomputer is a cross-platform and open source library for communication with dive computers from various manufacturers."
HOMEPAGE = "http://www.divesoftware.org/libdc/"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

DEPENDS = "libusb1"

inherit autotools pkgconfig

PV = "0.0.0"
SRCREV = "b7a6be15ca58cc8f89fbc8fa1a4c840e13d51092"
SRC_URI = "git://libdivecomputer.git.sourceforge.net/gitroot/libdivecomputer/libdivecomputer"
S = "${WORKDIR}/git"


