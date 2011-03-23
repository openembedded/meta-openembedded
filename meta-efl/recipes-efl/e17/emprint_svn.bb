DESCRIPTION = "Emprint is a utility for taking screenshots of the entire screen, a specific window, or a specific region."
LICENSE = "MIT BSD"
DEPENDS = "imlib2 virtual/libx11 ecore evas edje eina"
PV = "0.0.1+svnr${SRCPV}"
SRCREV = "${EFL_SRCREV}"

inherit e
SRC_URI = "${E_SVN}/trunk;module=${SRCNAME};proto=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"

FILES_${PN}-dbg += "${libdir}/${PN}/modules/.debug"
