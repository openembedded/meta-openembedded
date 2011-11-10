DESCRIPTION = "canutils (PTX flavour)"
HOMEPAGE = "http://www.pengutronix.de"
SECTION = "console/network"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "libsocketcan"

TAG = "canutils-${PV}"
SRC_URI = "git://git.pengutronix.de/git/tools/canutils.git;protocol=git;tag=${TAG} \
        "

S = "${WORKDIR}/git"

inherit autotools
