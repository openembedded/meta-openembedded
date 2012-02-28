require navit.inc

SRCREV = "4841"
PV = "0.2.0+svnr${SRCPV}"
PR = "${INC_PR}.2"

S = "${WORKDIR}/${PN}"
SRC_URI += "svn://anonymous@navit.svn.sourceforge.net/svnroot/navit/trunk;module=navit;proto=http "
