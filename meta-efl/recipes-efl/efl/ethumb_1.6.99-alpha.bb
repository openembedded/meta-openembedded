require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-alpha"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "356b996537136c6504daf1358509e5a8"
SRC_URI[sha256sum] = "1bdeff24945f67c5685bd526a2ba7a951a97fe3aff8aea2f6efc9cf8503c431b"
