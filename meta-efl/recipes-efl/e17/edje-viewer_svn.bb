DESCRIPTION = "Edje_Viewer is just that."
LICENSE = "MIT BSD"
DEPENDS = "elementary"
PV = "0.0.0+svnr${SRCPV}"
SRCREV = "${EFL_SRCREV}"

inherit e

SRCNAME = "edje_viewer"
SRC_URI = "${E_SVN}/trunk;module=${SRCNAME};proto=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"

FILES_${PN} += "${datadir}"
