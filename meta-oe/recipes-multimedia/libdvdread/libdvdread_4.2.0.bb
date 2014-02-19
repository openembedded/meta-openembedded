SUMMARY = "DVD access multimeda library"
SECTION = "libs/multimedia"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=64e753fa7d1ca31632bc383da3b57c27"
SRC_URI = "http://dvdnav.mplayerhq.hu/releases/libdvdread-${PV}.tar.bz2"

SRC_URI[md5sum] = "ab7a19d3ab1a437ae754ef477d6231a4"
SRC_URI[sha256sum] = "0bea15da842a4b04a482b009d72dcc6d9c9524ccc1bf67e5748319ec5ada8097"

inherit autotools lib_package binconfig pkgconfig

CONFIGUREOPTS_remove = "--disable-silent-rules"

do_configure_prepend() {
    # For some weird reason, libdvdread only provides a `configure2' script...
    cp ${S}/configure2 ${S}/configure
}
