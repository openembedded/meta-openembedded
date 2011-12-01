require efreet.inc

SRCREV = "${EFL_SRCREV_1.1.0}"
PV = "1.1.0+svnr${SRCPV}"
PR = "${INC_PR}.0"
DEFAULT_PREFERENCE = "-1"

SRC_URI = "\
  ${E_SVN}/trunk;module=${SRCNAME};proto=http;scmdata=keep \
"
S = "${WORKDIR}/${SRCNAME}"
