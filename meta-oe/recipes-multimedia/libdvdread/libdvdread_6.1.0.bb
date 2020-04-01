SUMMARY = "DVD access multimeda library"
SECTION = "libs/multimedia"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=64e753fa7d1ca31632bc383da3b57c27"

SRC_URI = "http://download.videolan.org/pub/videolan/libdvdread/${PV}/libdvdread-${PV}.tar.bz2"
SRC_URI[md5sum] = "0f74ad333331ff9bf441e74036f540df"
SRC_URI[sha256sum] = "501bbd7d1e7d5f2d2a0ff8d0a6ac2fbd7e5e7e0e80ef82fef818f43a30b4750c"

inherit autotools lib_package binconfig pkgconfig

CONFIGUREOPTS_remove = "--disable-silent-rules"
