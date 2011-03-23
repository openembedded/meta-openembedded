require efreet.inc

PR = "${INC_PR}.1"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${PV}.tar.gz \
  file://changeset_trunk_r57435.patch \
"

SRC_URI[md5sum] = "ce7dd05138335312c7eb9ac8e5e62467"
SRC_URI[sha256sum] = "000021480454f8684b9ad7a6a26fa7fe6c4530817ab3a695acb8ea722a78ebf9"
