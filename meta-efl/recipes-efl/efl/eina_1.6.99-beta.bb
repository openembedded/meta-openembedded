require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-beta"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "f5e123fdb6fb905c69f2f1f54bfe6257"
SRC_URI[sha256sum] = "051ebf7c4534c4d12f1aaaac7aa91cd877a2498e4c22336db8b07433c27f18c4"
