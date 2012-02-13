require python-efl.inc
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"
PR = "${INC_PR}.2"
SRCREV = "${EFL_SRCREV}"
DEPENDS += "edje python-evas"
RDEPENDS_${PN} += "python-evas"

SRC_URI += "file://0001-fix-unicode-conversion.patch"
