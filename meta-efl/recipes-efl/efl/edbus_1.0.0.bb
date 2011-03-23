require edbus.inc

PR = "${INC_PR}.0"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${PV}.tar.gz \
"
S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI[md5sum] = "4a3031a41e7e33f843cf104bf84cd4ee"
SRC_URI[sha256sum] = "701be8c07fd460fc3c0fa37f4753ceb5ef87cd5563f1ce5376fa486cf1a4acb5"
