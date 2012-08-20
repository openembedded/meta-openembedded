require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-beta"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "37ec5542731ad088c495e0ee6d7889c8"
SRC_URI[sha256sum] = "66bf53cee15549c4089307843f27a6a7e0c3bf7e576ab5091950ecc05009644a"
