require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-beta"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "b557e2e6c5988abf0695474b5763520b"
SRC_URI[sha256sum] = "b9c1262bc8e45b83c7b3643f9aefcebc88518953e4d4967ca655f1b325aac6ea"
