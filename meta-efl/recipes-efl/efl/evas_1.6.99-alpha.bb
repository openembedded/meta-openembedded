require ${BPN}.inc

PR = "${INC_PR}.0"

SRCVER = "1.7.0-alpha"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "3f4f1f87fa16dd4db121ce9c2e056131"
SRC_URI[sha256sum] = "25a905c90650e8d53a2b0cf90ef453dc7410f0143fd2fe1c11f5ccd50e107f70"
