require ${BPN}.inc

DEFAULT_PREFERENCE = "-1"
PV = "0.2.0+svnr${SRCPV}"
PR = "${INC_PR}.0"
SRCREV = "${EFL_SRCREV}"

SRC_URI = "${E_SVN}/trunk;module=${SRCNAME};protocol=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"
