SUMMARY = "A fast and lightweight IDE"
HOMEPAGE = "http://www.geany.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bd7b2c994af21d318bd2cd3b3f80c2d5"
DEPENDS = "gtk+ python-docutils-native"

inherit autotools pkgconfig perlnative pythonnative

SRC_URI = " \
    http://download.geany.org/${BP}.tar.bz2 \
    file://0001-configure.ac-remove-additional-c-test.patch \
"
SRC_URI[md5sum] = "888bd82d62759ac47a4a398fb27c8471"
SRC_URI[sha256sum] = "8ee41da28cead8c94d433e616d7ababa81727c63e9196ca6758ade3af14a49ef"

FILES_${PN} += "${datadir}/icons"
