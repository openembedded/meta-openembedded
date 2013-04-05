require ${BPN}.inc

PR = "${INC_PR}.0"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
  file://fix-ecore-fb-initialization.patch \
"

SRC_URI[md5sum] = "a41828b5b4ca42283d8658c0fe54db07"
SRC_URI[sha256sum] = "94e08f58acb5021727ecd4d6da0ac7dc3db672542ac5fcd71681835190ad6555"
