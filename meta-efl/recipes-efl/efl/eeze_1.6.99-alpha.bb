require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-alpha"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "25c36ea710d61b2f6a732ec44f9f5dbb"
SRC_URI[sha256sum] = "3440e11e9edbe234d207a8791a6c6502968ce642bd8d4821da77be71c11bd432"
