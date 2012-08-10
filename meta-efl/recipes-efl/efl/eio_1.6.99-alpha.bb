require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-alpha"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "741d2a2211a66cba0986b1dbcc12118f"
SRC_URI[sha256sum] = "1d3cbbc48b0970a8b7327d1d74ca44bc0a6f5852e26d348bc9793421675ca23f"
