require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-alpha"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "1505a60e8e446d55ab1887f3832063f2"
SRC_URI[sha256sum] = "6b6999e29836c56f079f84617133cc9b8d9bb1ea826577eebdc29f7ebeb6ffe5"
