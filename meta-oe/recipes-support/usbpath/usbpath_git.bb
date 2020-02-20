SUMMARY = "Convert the physical locations of a USB device to/from its number"
AUTHOR = "Werner Almesberger <werner@openmoko.org>"
SECTION = "console/utils"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://usbpath.c;endline=20;md5=0aa8c7d2af9110c78a99fbf9a504dc3f"
DEPENDS = "virtual/libusb0"
DEPENDS_class-native = "virtual/libusb0-native"

BBCLASSEXTEND = "native"

PV = "1.0+git${SRCPV}"

SRCREV = "0bde889e6fc09a330d0e0b9eb9808b20b2bf13d1"
SRC_URI = "git://github.com/openmoko/openmoko-svn.git;protocol=https;subpath=src/host/usbpath \
    file://configure.patch \
"
S = "${WORKDIR}/usbpath"

inherit autotools pkgconfig

RDEPENDS_${PN} += "perl"
