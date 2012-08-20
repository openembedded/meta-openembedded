require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-beta"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "2236f174981c94c4ad22c8e488d2187e"
SRC_URI[sha256sum] = "fe5339132ec514070af52243954bcb20408a12adfbe07d67d1c338fa3b1dc154"
