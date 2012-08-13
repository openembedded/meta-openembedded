require python-efl.inc
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a6a8e020838b23406c81b19c1d46df6"
DEPENDS += "elementary python-evas"
RDEPENDS_${PN} += "python-evas python-ecore python-edje" 
