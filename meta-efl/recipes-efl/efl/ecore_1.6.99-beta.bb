require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-beta"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
  file://fix-ecore-fb-initialization.patch \
"

SRC_URI[md5sum] = "12360540b2b8d46022cbf228c3bdef07"
SRC_URI[sha256sum] = "e3683d4b9be01347c564065bed36a77c6f42db56be7ea3cb6029a3b532afa1cb"
