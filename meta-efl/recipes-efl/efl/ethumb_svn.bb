require ${BPN}.inc

SRCREV = "${EFL_SRCREV}"
PV = "1.7.4+svnr${SRCPV}"
PR = "${INC_PR}.0"
DEFAULT_PREFERENCE = "-1"

SRCNAME_append = "-1.7"

SRC_URI = "\
    ${E_SVN}/branches;module=${SRCNAME};protocol=http;scmdata=keep \
"
S = "${WORKDIR}/${SRCNAME}"
