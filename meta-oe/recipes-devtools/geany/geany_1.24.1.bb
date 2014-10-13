SUMMARY = "A fast and lightweight IDE"
HOMEPAGE = "http://www.geany.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bd7b2c994af21d318bd2cd3b3f80c2d5"
DEPENDS = "gtk+"

inherit autotools pkgconfig perlnative

SRC_URI = " \
    http://download.geany.org/${BP}.tar.bz2 \
    file://0001-configure.ac-remove-additional-c-test.patch \
"
SRC_URI[md5sum] = "d225104cef3973164d70116d46239606"
SRC_URI[sha256sum] = "7fb505d9b01fe6874890525f837644a6a38c23a372bb068c65ef3673108a8c33"

FILES_${PN} += "${datadir}/icons"
