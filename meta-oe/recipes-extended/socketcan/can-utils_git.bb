SUMMARY = "Linux CAN network development utilities"
DESCRIPTION = "Linux CAN network development"
LICENSE = "GPLv2 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://include/linux/can.h;endline=44;md5=a9e1169c6c9a114a61329e99f86fdd31"

DEPENDS = "libsocketcan"

SRC_URI = "git://github.com/linux-can/${BPN}.git;protocol=git;branch=master"

SRCREV = "665d8699ebe728bf48e63e8ae58d2482db72f954"

PV = "0.0+gitr${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

