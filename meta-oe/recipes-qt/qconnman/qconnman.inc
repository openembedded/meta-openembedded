DESCRIPTION = "ConnMan management interface done in Qt"
DEPENDS = "connman"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PR = "r1"

inherit autotools

PV = "0.0+gitr${SRCPV}"
SRCREV = "9bab120f426e41552c369b7030414c792dd25a91"
SRC_URI = "git://github.com/OSSystems/qconnman.git"

S = "${WORKDIR}/git/"
