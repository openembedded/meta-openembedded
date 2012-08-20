require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-beta"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "cd0bbf6e93e521b0416d91e521bf81fd"
SRC_URI[sha256sum] = "f52ef395c341eaf68f844c038ce25512869ebd22f9abc86c9d32f1cf5e91c1df"
