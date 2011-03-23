DESCRIPTION = "EET is the Enlightenment data storage library"
DEPENDS = "pkgconfig zlib jpeg openssl eina gnutls"
LICENSE = "MIT BSD"

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
