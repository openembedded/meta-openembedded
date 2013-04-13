require ${BPN}.inc

PV = "0.17.0+svnr${SRCPV}"
PR = "${INC_PR}.0"

DEFAULT_PREFERENCE = "-1"

SRCREV = "${EFL_SRCREV}"

S = "${WORKDIR}/${SRCNAME}"

SRC_URI = "\
    ${E_SVN}/trunk;module=${SRCNAME};protocol=http \
    file://enlightenment_start.oe \
    file://applications.menu \
"
