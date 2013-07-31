DESCRIPTION = "EET is the Enlightenment data storage library"
DEPENDS = "pkgconfig zlib jpeg openssl eina gnutls"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=da947f414a2ca4323245f1abb1980953"

inherit efl

BBCLASSEXTEND = "native"

EXTRA_OECONF = "\
    --enable-openssl \
    --enable-cypher \
    --enable-signature \
    --disable-coverage \
    --enable-old-eet-file-format \
    --disable-assert \
"

PACKAGES =+ "${PN}-utils"

FILES_${PN}-utils = "\
    ${bindir}/${PN} \
"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "2db9527a95412af26df8f79429ffcc95"
SRC_URI[sha256sum] = "faad73b713c08f8f3c9ed76a0f8e4161f969e5ec8215db1dd5108d3f6eeb3a20"
