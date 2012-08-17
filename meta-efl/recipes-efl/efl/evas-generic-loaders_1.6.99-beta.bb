require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-beta"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "f4353d2743d255cf30cd7b6f946087e3"
SRC_URI[sha256sum] = "3dcc04689f89048ce1147698370dd51b854182b70c20704901ee1b3f03d4bd2a"
