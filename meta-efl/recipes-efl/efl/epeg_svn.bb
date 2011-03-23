DESCRIPTION = "Epeg is a small library for handling thumbnails."
LICENSE = "MIT"
DEPENDS = "jpeg"
PV = "0.9.0+svnr${SRCPV}"
PR = "r2"
SRCREV = "${EFL_SRCREV_1.0.0}"

inherit efl
SRC_URI = "${E_SVN}/OLD;module=${SRCNAME};proto=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"
