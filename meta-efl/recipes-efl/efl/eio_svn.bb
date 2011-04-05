require eio.inc

SRCREV = "${EFL_SRCREV}"
PV = "0.1.0+svnr${SRCPV}"
PR = "${INC_PR}.0"
DEFAULT_PREFERENCE = "-1"

LIC_FILES_CHKSUM = "file://COPYING;md5=403f20cf42c847686ac2956f14bfa30f"

SRC_URI = "\
  ${E_SVN}/trunk;module=${SRCNAME};proto=http;scmdata=keep \
"
S = "${WORKDIR}/${SRCNAME}"
