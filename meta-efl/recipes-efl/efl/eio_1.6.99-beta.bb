require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-beta"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "5346403eca950b6f8f1c886451e75ab8"
SRC_URI[sha256sum] = "2cc78170277654e57cc69b49856da3c8ede425f4c8fdf4df702f46184806ac3f"
