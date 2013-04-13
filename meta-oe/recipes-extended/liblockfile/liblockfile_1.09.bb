DESCRIPTION = "File locking library."
SECTION = "libs"
LICENSE = "LGPLv2+ & GPLv2+"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=ac284a60d48eaa4bc811cddc377fa341"

SRC_URI = "${DEBIAN_MIRROR}/main/libl/liblockfile/liblockfile_1.09.orig.tar.gz \
    file://install.patch \
    file://configure.patch \
    file://ldflags.patch \
"

SRC_URI[md5sum] = "2aa269e4405ee8235ff17d1b357c6ae8"
SRC_URI[sha256sum] = "16979eba05396365e1d6af7100431ae9d32f9bc063930d1de66298a0695f1b7f"

inherit autotools

EXTRA_OECONF = "--enable-shared --enable-static"

do_install () {
    oe_runmake 'ROOT=${D}' INSTGRP='' install
}

