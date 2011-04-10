DESCRIPTION = "USB Device Firmware Upgrade utility"
SECTION = "devel"
AUTHOR = "Harald Welte <laforge@openmoko.org>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
SRCREV = "4160"
PV = "0.1+svnr${SRCPV}"
PR = "r2"

DEPENDS = "virtual/libusb0 usbpath"

SRC_URI = "svn://svn.openmoko.org/trunk/src/host/;module=dfu-util;proto=http"
S = "${WORKDIR}/dfu-util"

inherit autotools

