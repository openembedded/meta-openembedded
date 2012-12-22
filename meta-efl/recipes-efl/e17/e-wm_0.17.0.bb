require ${BPN}.inc

PR = "${INC_PR}.0"

SRCNAME = "enlightenment"

S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
  file://enlightenment_start.oe \
  file://applications.menu \
"

SRC_URI[md5sum] = "b2824849c2c56d80fdc11c1f2171d4ec"
SRC_URI[sha256sum] = "469a493c6f7fb2599912edb5ec17181554187c2845bb5290878f1eba9e04b258"
