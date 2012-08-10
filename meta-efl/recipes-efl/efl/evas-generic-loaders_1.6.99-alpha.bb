require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-alpha"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "e8444bb1ddebd337a8d0bd8a14d83951"
SRC_URI[sha256sum] = "84f1d1e3b8a232c849f91ff70e2b5ad2154a5c2e9904469b3b1ec5b41dd700c7"
