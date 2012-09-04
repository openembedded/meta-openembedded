require ${BPN}.inc
PR = "${INC_PR}.0"
SRCREV = "${EFL_SRCREV}"
PV = "1.7.0+svnr${SRCPV}"
DEFAULT_PREFERENCE = "-1"

SRC_URI = "${E_SVN}/trunk/BINDINGS/python;module=${SRCNAME};protocol=http"
S = "${WORKDIR}/${SRCNAME}"

SRC_URI += "file://0001-fix-unicode-conversion.patch"
