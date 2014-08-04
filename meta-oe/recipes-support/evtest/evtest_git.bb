SUMMARY = "Simple tool for input event debugging"
HOMEPAGE = "http://people.freedesktop.org/~whot/evtest/"
AUTHOR = "Vojtech Pavlik <vojtech@suse.cz>"
SECTION = "console/utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "libxml2"

SRCREV = "67f3dfefb0b5708ad5b692f19c1a3a5b135264ca"
SRC_URI = "git://anongit.freedesktop.org/evtest;protocol=git"

PV = "1.25+${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools
