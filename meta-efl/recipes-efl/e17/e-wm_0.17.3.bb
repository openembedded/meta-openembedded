require ${BPN}.inc

PR = "${INC_PR}.0"

SRCNAME = "enlightenment"

S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
    file://enlightenment_start.oe \
    file://applications.menu \
"

SRC_URI[md5sum] = "e20f4d75a40f3de0dcd2862ca0483ebc"
SRC_URI[sha256sum] = "fb4856c705633f8cbac7b576387204d56a1dbd652005b11a0e5bfdf0f1ca389e"
