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

SRC_URI[md5sum] = "225eadf514465be321a44a003d687a55"
SRC_URI[sha256sum] = "3345e0889bbc29536dbe0f6236ed1ae6a9d685b8e39877d5404fa6217bd12ec6"
