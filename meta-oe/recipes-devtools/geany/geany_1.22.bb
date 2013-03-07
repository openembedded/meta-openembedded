DESCRIPTION = "A fast and lightweight IDE"
HOMEPAGE = "http://www.geany.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=c107cf754550e65755c42985a5d4e9c9"
DEPENDS = "gtk+"
PR = "r2"

inherit autotools pkgconfig perlnative

SRC_URI = " \
    http://download.geany.org/${PN}-${PV}.tar.bz2 \
    file://0001-configure.ac-remove-additional-c-test.patch \
"
SRC_URI[md5sum] = "0672077fe83e2a739aa0eaca426aacf0"
SRC_URI[sha256sum] = "901a35a7395ef10a80fb10b3ab63bae3871693a4e82d56388e9521a27877577e"

FILES_${PN} += "${datadir}/icons"
