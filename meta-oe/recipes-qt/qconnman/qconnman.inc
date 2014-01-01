DESCRIPTION = "ConnMan management interface done in Qt"
DEPENDS = "connman"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PR = "r2"

inherit autotools

PV = "0.0+gitr${SRCPV}"
SRCREV = "f976b18c7c5584627224784801803e9fd3ebe0ff"
SRC_URI = "git://github.com/OSSystems/qconnman-ui.git;branch=stable"

S = "${WORKDIR}/git/"
