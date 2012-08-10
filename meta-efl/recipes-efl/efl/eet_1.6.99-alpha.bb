require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-alpha"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "06a65f37117607ba24e4f4a9d3420f0a"
SRC_URI[sha256sum] = "29b6717404131b30882ae5f7947f5705da972f07576ad4c6c6c378a7592b5f68"
