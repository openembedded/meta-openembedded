require ${BPN}.inc

PR = "${INC_PR}.0"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${PV}.tar.gz \
  file://fix-ecore-fb-initialization.patch \
"

SRC_URI[md5sum] = "557f8411d323b14ed7bf1be368ab302a"
SRC_URI[sha256sum] = "78d8374e5f901f1dd4c89e99508a5a3470b8a0cb2510ab5b7e45d670e6621ef8"
