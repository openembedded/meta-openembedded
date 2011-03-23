DESCRIPTION = "Edb is the Enlightenment database library"
LICENSE = "MIT BSD"
DEPENDS = "zlib"
PV = "1.0.5.050+svnr${SRCPV}"
PR = "r1"
SRCREV = "${EFL_SRCREV_1.0.0}"

inherit efl

SRC_URI = "${E_SVN}/OLD;module=${SRCNAME};proto=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"
