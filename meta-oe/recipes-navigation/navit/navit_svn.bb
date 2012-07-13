require navit.inc

SRCREV = "5159"
PV = "0.2.0+svnr${SRCPV}"
PR = "${INC_PR}.2"

S = "${WORKDIR}/${PN}"
SRC_URI += "svn://anonymous@navit.svn.sourceforge.net/svnroot/navit/trunk;module=navit;protocol=http \
  file://taking-address-of-temporary-array.patch \
"
