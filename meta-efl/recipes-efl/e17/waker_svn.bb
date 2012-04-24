DESCRIPTION = "An EFL base at/alarm wakeup daemon"
LICENSE = "LGPL"
DEPENDS = "ecore edbus"
SECTION = "console"
PV = "0.0.0+svnr${SRCPV}"
SRCREV = "${EFL_SRCREV}"

inherit efl

SRC_URI = "\
  ${E_SVN}/trunk/TMP/st;module=${SRCNAME};proto=http \
  file://0001-waker-remove-old-ecore_string_init-and-ecore_string_.patch \
"
S = "${WORKDIR}/${SRCNAME}"

FILES_${PN} += "${bindir}/* ${libdir}/lib*.so.*"
FILES_${PN} += "/etc/X11/Xsession.d/80x-enlightenment-wakerd"
