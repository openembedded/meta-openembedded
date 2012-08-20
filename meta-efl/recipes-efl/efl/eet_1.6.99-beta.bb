require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-beta"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "1103a2a0e5022c42dcb4ccacc5db8881"
SRC_URI[sha256sum] = "156aa6ef6880cf45b25c6cb45bf659ebb2e9bb207c11a479f504cd795c030c6b"
