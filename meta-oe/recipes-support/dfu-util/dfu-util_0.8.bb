DESCRIPTION = "USB Device Firmware Upgrade utility"
SECTION = "devel"
AUTHOR = "Harald Welte <laforge@openmoko.org>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"


SRC_URI = "http://dfu-util.gnumonks.org/releases/${BP}.tar.gz \
    file://0001-configure.ac-Don-t-check-for-usbpath.patch \
"

inherit autotools pkgconfig

DEPENDS = "libusb1"

SRC_URI[md5sum] = "fc6daf6b0ee57d7e40ffa3e8111023d1"
SRC_URI[sha256sum] = "55cbde9be12a212bd84bce9d1e63941d9a16139ed0d4912401367eba1502f058"
