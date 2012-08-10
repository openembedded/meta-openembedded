require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-alpha"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "6833e32681db6630a4a50d98cf5cac88"
SRC_URI[sha256sum] = "8810a40568df9c068c1606821f24a95fb409e0f99459a45b18ee07077f432cc2"
