DESCRIPTION = "Engrave is an Edje Editing Library"
LICENSE = "MIT"
# also requires yacc and lex on host
DEPENDS = "evas ecore"
PV = "0.0.0+svnr${SRCPV}"
SRCREV = "${EFL_SRCREV}"

inherit efl
SRC_URI = "${E_SVN}/OLD;module=${SRCNAME};proto=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"
