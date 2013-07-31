DESCRIPTION = "Enlightenment Input Output Library"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=180fca752525726bd6ba021689509a08"
DEPENDS = "ecore eina"

inherit efl

BBCLASSEXTEND = "native"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "a5e1608a629667de3cfd17289d599b07"
SRC_URI[sha256sum] = "f18831389f6b735e73c74d7f411602722dc8ce214ac3f4171b59d216d842a3b0"
