require ${BPN}.inc

PR = "${INC_PR}.0"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
  file://0001-evas-common_16-add-missing-FRIBIDI_CFLAGS.patch \
"

SRC_URI[md5sum] = "4bcd5a4721f565bc7dd974973001ef73"
SRC_URI[sha256sum] = "793b5bc4795cd26e2c10475e1f1647bbc5e052170f6212936b7eb95b87782608"
