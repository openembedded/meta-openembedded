SUMMARY = "A client-side C library implementing the SSH2 protocol"
HOMEPAGE = "http://www.libssh2.org/"
SECTION = "libs"

DEPENDS = "zlib openssl"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=c5cf34fc0acb44b082ef50ef5e4354ca"

SRC_URI = "http://www.libssh2.org/download/${BP}.tar.gz"
SRC_URI[md5sum] = "00aabd6e714a5f42a4fb82ace20db1dd"
SRC_URI[sha256sum] = "5a202943a34a1d82a1c31f74094f2453c207bf9936093867f41414968c8e8215"

inherit autotools pkgconfig

EXTRA_OECONF += "--with-openssl \
                --with-libz \
                "
