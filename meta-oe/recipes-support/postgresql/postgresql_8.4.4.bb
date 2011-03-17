require postgresql.inc

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=29b81b066680be9ffd98cb9d2afb9de6"

DEFAULT_PREFERENCE = "-1"

SRC_URI = "ftp://ftp.postgresql.org/pub/source/v${PV}/${P}.tar.bz2 \
           file://remove.autoconf.version.check.patch"

SRC_URI[md5sum] = "4bf2448ad965bca3940df648c02194df"
SRC_URI[sha256sum] = "e66b398d565f7fb16d8ae58ae72881dcd3dbb1b88f532bbe1c2d1284812be37e"
