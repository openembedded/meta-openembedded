require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-beta"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "0978e24b5ea5a0d0b5eb79a7f6d7d1af"
SRC_URI[sha256sum] = "5bd52116499a5686023a563ed9102e815292786dff19d617d3cc92829c624caf"
