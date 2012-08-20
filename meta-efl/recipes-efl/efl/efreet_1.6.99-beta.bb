require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-beta"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "35f4475b81da6b9bf9b3c022fd0947ab"
SRC_URI[sha256sum] = "b24ee9d2624804b3828b6458eddaf3e74c3d2deac58e15e33e6b183d60989a75"
