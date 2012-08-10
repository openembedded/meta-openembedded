require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-alpha"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "b4fdbb79b92496829b921d46762344ac"
SRC_URI[sha256sum] = "51a7799ba61036072be646e1fcaa742d9c5f39bb499155012bc177a9f561f2b5"
