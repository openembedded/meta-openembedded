require ${BPN}.inc

SRCREV = "${EFL_SRCREV}"
PV = "1.2.1+svnr${SRCPV}"
PR = "${INC_PR}.0"
DEFAULT_PREFERENCE = "-1"

# it needs to be disabled, because creates circular dependency on elementary
EXTRA_OECONF += "--disable-edbus-performance-test"

SRC_URI = "\
  ${E_SVN}/trunk;module=${SRCNAME};protocol=http;scmdata=keep \
"
S = "${WORKDIR}/${SRCNAME}"

FILES_${PN} += "${datadir}/e_dbus/logo.png"
