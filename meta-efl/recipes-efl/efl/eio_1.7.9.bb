DESCRIPTION = "Enlightenment Input Output Library"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=180fca752525726bd6ba021689509a08"
DEPENDS = "ecore eina"

inherit efl

BBCLASSEXTEND = "native"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "f4d6cbd216a9ae5983fa8ee8dadf04cb"
SRC_URI[sha256sum] = "35ce08ecf6afd5faa4fe857764537e15aede2b0d2c37922e55e97e2dff3352de"
