require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-alpha"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "b4c063f673394bda3f24b1a894001224"
SRC_URI[sha256sum] = "4b8aeb5e59b493e9cfce30c91acf9b55f4774b417349c8291fda6e35265ee311"
