require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-beta"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "07c1bdb6766391fa972e2f1a4c480413"
SRC_URI[sha256sum] = "7bbad4db88e82a6e0206942c859d4bbbba261cbaa4530f343664538456eef7c9"
