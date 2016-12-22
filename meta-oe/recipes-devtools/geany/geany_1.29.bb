SUMMARY = "A fast and lightweight IDE"
HOMEPAGE = "http://www.geany.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bd7b2c994af21d318bd2cd3b3f80c2d5"
DEPENDS = "gtk+ python3-docutils-native"

inherit autotools pkgconfig perlnative pythonnative

SRC_URI = "http://download.geany.org/${BP}.tar.bz2"
SRC_URI[md5sum] = "9b62a443461cc917b41c94fa7d58fdb1"
SRC_URI[sha256sum] = "394307596bc908419617e4c33e93eae8b5b733dfc8d01161677b8cbd3a4fb20f"

FILES_${PN} += "${datadir}/icons"

EXTRA_OECONF = "--disable-html-docs"
