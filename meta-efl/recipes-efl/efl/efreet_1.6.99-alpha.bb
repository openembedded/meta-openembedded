require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-alpha"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "d18062fb8831334323b00123fe93ac5b"
SRC_URI[sha256sum] = "28372b8b3c064fc0f6c50964d1199aee28b06102d796c1546de7c7bf8d41b97c"
