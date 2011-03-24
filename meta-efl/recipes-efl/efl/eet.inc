DESCRIPTION = "EET is the Enlightenment data storage library"
DEPENDS = "pkgconfig zlib jpeg openssl eina gnutls"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=4ca74999c2a19695474d532d2f2ac886"

inherit efl

BBCLASSEXTEND = "native"
INC_PR = "r0"

EXTRA_OECONF = "\
  --enable-openssl \
  --enable-cypher \
  --enable-signature \
  --disable-coverage \
  --enable-old-eet-file-format \
  --disable-assert \
"
