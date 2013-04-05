require ${BPN}.inc

PR = "${INC_PR}.0"

SRCNAME = "enlightenment"

S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
  file://enlightenment_start.oe \
  file://applications.menu \
"

SRC_URI[md5sum] = "ab15be89ec0ed06271fcca60b221d83b"
SRC_URI[sha256sum] = "63882b29164497c71b17312cbc5a5c6911475b0ccf989502e179b1e3fee3e3b9"
