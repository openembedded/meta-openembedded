require ${BPN}.inc

PR = "${INC_PR}.0"

SRCNAME = "enlightenment"

S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
  file://enlightenment_start.oe \
  file://applications.menu \
"

SRC_URI[md5sum] = "fcd29635328efa2bd207f18fe230e41a"
SRC_URI[sha256sum] = "2d9327158b1bdf4301d898a1dca7d94d3e480f6e595dee05809dc66969df5dd3"
