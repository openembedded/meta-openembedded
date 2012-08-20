require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-beta"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "7b8719248d5745683201bbf11b8d510f"
SRC_URI[sha256sum] = "f802272d9a7a4678aef736ca24ef1818dbd9f8524c963da6c5680abdf71d05e4"
