require ${BPN}.inc

PR = "${INC_PR}.0"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
  file://fix-ecore-fb-initialization.patch \
"

SRC_URI[md5sum] = "f6d086ea8bbe64651dc3b5d2e840d106"
SRC_URI[sha256sum] = "0b9642a67f0d977c2a13ed83ac02387c99309db92a72bf1031482df65ee4a1c7"
