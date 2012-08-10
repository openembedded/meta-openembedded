require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-alpha"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
  file://fix-ecore-fb-initialization.patch \
"

SRC_URI[md5sum] = "c40c25f2638677191bb95a628743209d"
SRC_URI[sha256sum] = "8742fdcb725ac1494650577f75bbd36d3bf42d8d5c1f8d96a9d541f310a46c44"
