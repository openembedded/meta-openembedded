DESCRIPTION = "DirectFB extra providers"
DEPENDS = "directfb"
SECTION = "libs"
LICENSE = "GPL"

SRC_URI = " \
           http://www.directfb.org/downloads/Extras/DirectFB-examples-${PV}.tar.gz \
          "
S = "${WORKDIR}/DirectFB-examples-${PV}"

inherit autotools

SRC_URI[md5sum] = "ce018f681b469a1d72ffc32650304b98"
SRC_URI[sha256sum] = "830a1bd6775d8680523596a88a72fd8e4c6a74bf886d3e169b06d234a5cf7e3e"
