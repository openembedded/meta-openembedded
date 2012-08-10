require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-alpha"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "55bea7e3c3e5d503a98f336d829b47bb"
SRC_URI[sha256sum] = "b54e9ea93c248885b56e5b6dbf8161bf9cd04103dcd06d72eb2579d83a48ad7f"
