require python-efl.inc
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a6a8e020838b23406c81b19c1d46df6"
PR = "${INC_PR}.2"
SRCREV = "${EFL_SRCREV}"
DEPENDS += "elementary python-evas"
RDEPENDS_${PN} += "python-evas python-ecore python-edje" 

SRC_URI += " file://0001-python-elementary-temporary-fix-for-build-breakage.patch"
