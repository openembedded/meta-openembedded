require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-alpha"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "7509fb6bc2d814066deed3d97c2d0118"
SRC_URI[sha256sum] = "a262b9723f4491e4fbb43b285d7845a6dc8daab53d97e68cd8b0df69737ff69e"
