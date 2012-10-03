DESCRIPTION = "USB CEC Adaptor communication Library"
HOMEPAGE = "http://libcec.pulse-eight.com/"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=5e8e16396992369f73f3d28875f846da"

DEPENDS = "udev lockdev"

PV = "1.9.0"

SRCREV = "9884e9ffc5293de5bb9092db1ed581f213a678df"
SRC_URI = "git://github.com/Pulse-Eight/libcec.git"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

# cec-client and xbmc need the .so present to work :(
FILES_${PN} += "${libdir}/*.so"
INSANE_SKIP_${PN} = "dev-so"

# Adapter shows up as a CDC-ACM device
RRECOMMENDS_${PN} = "kernel-module-cdc-acm"
