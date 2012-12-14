require ${BPN}.inc

PR = "${INC_PR}.0"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
  file://fix-ecore-fb-initialization.patch \
"

SRC_URI[md5sum] = "355ae8ec2793c89dc93ced936d853b89"
SRC_URI[sha256sum] = "0e2facbc89dbf5182b5bf5628bcdb37fa837e7b5978ce3f4bf9839cfe06a8830"
