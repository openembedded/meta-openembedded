require ${BPN}.inc

PR = "${INC_PR}.0"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
  file://fix-ecore-fb-initialization.patch \
"

SRC_URI[md5sum] = "3b1aa01bffb10d4266545fe4a823bb47"
SRC_URI[sha256sum] = "2fc1d202c688466b0f33e21c3b892c7610ae5f89835475a1ebdb22c72c0d9116"
