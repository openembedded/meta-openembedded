require ${BPN}.inc

PR = "${INC_PR}.0"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
  file://fix-ecore-fb-initialization.patch \
"

SRC_URI[md5sum] = "cf0b5e980a77dc0d1bdedf2bcc60e862"
SRC_URI[sha256sum] = "f009e17376b7f3fb6ce6b45648d2a0131a152943b502fa34cc4bd018f67da8bb"
