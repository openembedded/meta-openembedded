DESCRIPTION = "A fast and lightweight IDE"
HOMEPAGE = "http://www.geany.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bd7b2c994af21d318bd2cd3b3f80c2d5"
DEPENDS = "gtk+"

inherit autotools pkgconfig perlnative

SRC_URI = " \
    http://download.geany.org/${PN}-${PV}.tar.bz2 \
    file://0001-configure.ac-remove-additional-c-test.patch \
"
SRC_URI[md5sum] = "4290e8c32305e916f57bb0f0233bbec2"
SRC_URI[sha256sum] = "cdd4a772694803c837ae59e56f7bdc2faba174509317211f522e7d25dfcbe8b0"

FILES_${PN} += "${datadir}/icons"
