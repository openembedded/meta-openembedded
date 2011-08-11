require dfu-util.inc

DEPENDS = "libusb1"

SRC_URI += "file://no-usbpath.patch"

SRC_URI[md5sum] = "2cf466fabb881e8598fa02f286d3242c"
SRC_URI[sha256sum] = "f60fea987aa06ee03da22a656d1d113ac224458ec4442bcf1764a62f0930bd07"
