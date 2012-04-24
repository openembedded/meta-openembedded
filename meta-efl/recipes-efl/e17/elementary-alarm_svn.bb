DESCRIPTION = "An Elementary based Alarm app"
LICENSE = "GPL"
DEPENDS = "elementary"
SECTION = "x11"
PV = "0.0.0+svnr${SRCPV}"
SRCREV = "${EFL_SRCREV}"

inherit e
SRC_URI = "${E_SVN}/trunk/TMP/st;module=${SRCNAME};proto=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"

RDEPENDS_${PN} = "waker"
FILES_${PN} += "${bindir}/*"
FILES_${PN} += "${datadir}/${PN}"
FILES_${PN} += "${datadir}/icons/*"
FILES_${PN} += "${datadir}/applications/*"
FILES_${PN} += "${datadir}/${PN}/sounds/*"
