require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-beta"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "542a8931b85675e497df0c290103ca87"
SRC_URI[sha256sum] = "e63fe9c67d3ec5b6bed06a4c49f70fbb93e30481f36f35ca2ee9810cbaf85868"
