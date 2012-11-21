require ${BPN}.inc

PR = "${INC_PR}.0"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
  file://r79484.patch \
"

SRC_URI[md5sum] = "f0be474e4b4d51704dc64ec27941c2ad"
SRC_URI[sha256sum] = "d794b8b2144786cc42c34302660d130211aa0dc223fa399fdabadaee3397d575"
