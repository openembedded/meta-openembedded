require ${BPN}.inc

PR = "${INC_PR}.0"

SRCNAME = "enlightenment"

S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
    file://enlightenment_start.oe \
    file://applications.menu \
"

SRC_URI[md5sum] = "dd6ffbe1ee6a804dbd2a7b2645bf3b4d"
SRC_URI[sha256sum] = "66d250a865ffb97b12cabe8081ae041ce6924f55ffffaca6c65d468bb9f902f9"
