DESCRIPTION = "USB CEC Adaptor communication Library"
HOMEPAGE = "http://libcec.pulse-eight.com/"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=185ead350fec5fc223da0f65f9a802af"

DEPENDS = "udev"

PV = "1.4.0"
SRCREV = "2db8981f49fbd167ddbbf19c1fbadd064abc332a"
SRC_URI = "git://github.com/Pulse-Eight/libcec.git"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

