require ${BPN}.inc

PR = "${INC_PR}.0"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
  file://fix-ecore-fb-initialization.patch \
"

SRC_URI[md5sum] = "96f85bcb720db2c723bf296b4d5d0bb6"
SRC_URI[sha256sum] = "71618e58564b19705f9d02cf69d0459400e15d6b2a55baf53784c5dc5ec89c42"
