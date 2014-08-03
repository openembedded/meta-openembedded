DESCRIPTION = "USB Device Firmware Upgrade utility"
SECTION = "devel"
AUTHOR = "Harald Welte <laforge@openmoko.org>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "http://${BPN}.gnumonks.org/releases/${BP}.tar.gz \
    file://0001-configure.ac-Don-t-check-for-usbpath.patch \
"

inherit autotools pkgconfig

DEPENDS = "libusb1"

SRC_URI[md5sum] = "56844020177d4db4c1ea2e926fe9d588"
SRC_URI[sha256sum] = "f52a2a5489fbf9f3204a6ada05e0b47ee322e19d81c712e0c58a332d80ec3eab"
