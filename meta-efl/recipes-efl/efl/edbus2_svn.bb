require ${BPN}.inc

SRCREV = "${EFL_SRCREV}"
PV = "1.7.99+svnr${SRCPV}"
PR = "${INC_PR}.2"
DEFAULT_PREFERENCE = "-1"

SRC_URI = "\
    ${E_SVN}/trunk/IN-EFL;module=${SRCNAME};protocol=http;scmdata=keep \
"
S = "${WORKDIR}/${SRCNAME}"

PACKAGES =+ "${PN}-utils"
FILES_${PN}-utils = "${bindir}/edbus-codegen"
