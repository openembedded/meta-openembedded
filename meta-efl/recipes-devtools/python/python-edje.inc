require python-efl.inc
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"
DEPENDS += "edje python-evas evas"
RDEPENDS_${PN} += "python-evas"
EXTRA_OECONF += "--disable-examples"
