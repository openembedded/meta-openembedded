require ${BPN}.inc

PR = "${INC_PR}.0"

SRCNAME = "enlightenment"

S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
  file://enlightenment_start.oe \
  file://applications.menu \
"

SRC_URI[md5sum] = "e78018ba7a4622efbcbebd6d093b7fa4"
SRC_URI[sha256sum] = "5e7b7f5ec88d0589bb4db8aae2c198dc9fd91049419e2460cbd7631207ebd799"
