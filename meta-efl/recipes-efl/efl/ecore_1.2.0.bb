require ${BPN}.inc

PR = "${INC_PR}.0"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${PV}.tar.gz \
  file://fix-ecore-fb-initialization.patch \
"

SRC_URI[md5sum] = "64362b8ab96776a5868db4e6a8919926"
SRC_URI[sha256sum] = "97c0f07180fcf7dec6ce39893b206ff069109ef4569ea6be2fdbb2e05f029aa9"
