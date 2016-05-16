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
SRC_URI[md5sum] = "7c23f835f45def89d10884c1343fa29e"
SRC_URI[sha256sum] = "846ff699a5944c5c3c068ae0199d4c13946a668bfc6d03f8c79765667c20cadf"

FILES_${PN} += "${datadir}/icons"
