DESCRIPTION = "DirectFB extra providers"
DEPENDS = "directfb"
SECTION = "libs"
LICENSE = "GPL"

SRC_URI = " \
           http://www.directfb.org/downloads/Old/DirectFB-examples-${PV}.tar.gz \
          "
S = "${WORKDIR}/DirectFB-examples-${PV}"

inherit autotools

SRC_URI[md5sum] = "0cdfb4dd248eada3dc35db4f8cf75f8d"
SRC_URI[sha256sum] = "c54e779a720841126e23d692de85243e23b0d94fb7792a958b96b5bd097a8b85"
