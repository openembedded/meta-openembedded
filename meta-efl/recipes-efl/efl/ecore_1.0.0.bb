require ecore.inc

PR = "${INC_PR}.0"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${PV}.tar.gz \
  file://fix-ecore-fb-initialization.patch \
  file://exit_uclibc.patch \
"

SRC_URI[md5sum] = "56fa814282f48c346b398e16de81145f"
SRC_URI[sha256sum] = "93a15704a9ba8126fac0da5c8802f09cecd0b68547121a4ab4146113f0d15eb7"
