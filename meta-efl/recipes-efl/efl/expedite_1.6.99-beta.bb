require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-alpha"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "fdd425395b8380b5c3b39186f9ef7ff3"
SRC_URI[sha256sum] = "738eed02cefa2c57cb7ac0fc8103d1680b6d3a4a17d620dd5fc503b0128bf1a5"
