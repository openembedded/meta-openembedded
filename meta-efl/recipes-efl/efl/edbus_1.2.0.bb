require ${BPN}.inc

PR = "${INC_PR}.0"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${PV}.tar.gz \
"
S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI[md5sum] = "19dc0e43cd9defd5b8fdc7c8610bfe2c"
SRC_URI[sha256sum] = "2f72b3710ae66604e37e6f6b20857caae9e2ddc7002fa402806dce14b6acbd4c"
