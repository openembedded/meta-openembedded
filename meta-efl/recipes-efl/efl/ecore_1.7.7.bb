require ${BPN}.inc

PR = "${INC_PR}.0"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
    file://fix-ecore-fb-initialization.patch \
"

SRC_URI[md5sum] = "8974d55e80bab1405c24b73f1f43153c"
SRC_URI[sha256sum] = "1a933394897bba8d255c3e458501be9ed30fa2a02f7552e338350f54e2342fd6"
