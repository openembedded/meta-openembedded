DESCRIPTION = "Edb is the Enlightenment database library"
LICENSE = "MIT BSD"
DEPENDS = "zlib"
PV = "1.0.5.050+svnr${SRCPV}"
SRCREV = "${EFL_SRCREV}"

inherit efl

SRC_URI = "${E_SVN}/OLD;module=${SRCNAME};proto=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"
