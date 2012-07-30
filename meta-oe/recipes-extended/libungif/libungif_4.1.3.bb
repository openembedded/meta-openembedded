DESCRIPTION = "shared library for GIF images"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=ae11c61b04b2917be39b11f78d71519a"

SRC_URI = "${SOURCEFORGE_MIRROR}/giflib/libungif-${PV}.tar.bz2"
SRC_URI[md5sum] = "8c198831cc0495596c78134b8849e9ad"
SRC_URI[sha256sum] = "59e835a57e6df03e4d2253b2357253f3d13da9473ff465563a3b9833a744fc36"

inherit autotools lib_package

PACKAGES =+ "${PN}-utils"

FILES_${PN}-utils = "${bindir}/*"
