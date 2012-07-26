require ${BPN}.inc

SRCREV = "${EFL_SRCREV}"
PV = "1.2.1+svnr${SRCPV}"
PR = "${INC_PR}.0"
DEFAULT_PREFERENCE = "-1"

EXTRA_OECONF += "--disable-performance_test"
SRC_URI = "\
  ${E_SVN}/trunk;module=${SRCNAME};protocol=http;scmdata=keep \
  file://0001-edbus-configure.ac-fixup-performance_test.patch \
"
S = "${WORKDIR}/${SRCNAME}"
