require ${BPN}.inc

SRCREV = "${EFL_SRCREV}"
PV = "1.2.0+svnr${SRCPV}"
PR = "${INC_PR}.0"
DEFAULT_PREFERENCE = "-1"

SRC_URI = "\
  ${E_SVN}/trunk;module=${SRCNAME};proto=http;scmdata=keep \
"
S = "${WORKDIR}/${SRCNAME}"
