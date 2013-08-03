require ${BPN}.inc

SRCNAME = "enlightenment"

S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
    file://enlightenment_start.oe \
    file://applications.menu \
"

SRC_URI[md5sum] = "ce3b7c8f05975521bdfe1edb72c8e245"
SRC_URI[sha256sum] = "090447607c850f98ce8c2089c55e15d77b35a9014eddff9119f38b0e0e49f0b7"
