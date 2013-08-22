SUMMARY = "SSL encryption wrapper between remote client and local (inetd-startable) or remote server."
SECTION = "net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=f41ebed8571077706fee0b860c4d6666"
DEPENDS = "openssl"

SRC_URI = "https://www.stunnel.org/downloads/${BP}.tar.gz"

SRC_URI[md5sum] = "ac4c4a30bd7a55b6687cbd62d864054c"
SRC_URI[sha256sum] = "9cae2cfbe26d87443398ce50d7d5db54e5ea363889d5d2ec8d2778a01c871293"

inherit autotools

EXTRA_OECONF += "--with-ssl='${STAGING_INCDIR}' --disable-fips"
