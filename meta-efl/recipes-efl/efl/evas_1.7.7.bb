require ${BPN}.inc

PR = "${INC_PR}.2"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
    file://revert.r83789.patch \
"

SRC_URI[md5sum] = "980abd6f645a38aa0a5d74f11a0e07f9"
SRC_URI[sha256sum] = "ea8403d58cd079a651c515e76d7812deb0f7c2f41bb9807b3040a023b2fba78c"
