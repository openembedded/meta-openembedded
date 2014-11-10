DESCRIPTION = "USB Device Firmware Upgrade utility"
SECTION = "devel"
AUTHOR = "Harald Welte <laforge@openmoko.org>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

S = "${WORKDIR}/git"

SRC_URI = "git://gitorious.org/dfu-util/dfu-util.git \
    file://0001-configure.ac-Don-t-check-for-usbpath.patch \
"
SRCREV="a0a3668e0571a9b007f7d62b01e7dcfd7754ce50"

inherit autotools pkgconfig

DEPENDS = "libusb1"

SRC_URI[md5sum] = "1de724551604bce1962960b7a301cc08"
SRC_URI[sha256sum] = "5d253f924fd29bc13054b664bba9aa86b865299971195969478253c1775b7e56"
