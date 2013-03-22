DESCRIPTION = "DVD access multimeda library"
SECTION = "libs/multimedia"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
SRC_URI = "git://daniel-baumann.ch/git/debian/libdvdread.git;protocol=http;branch=debian"

PR = "r1"

SRCREV = "a733b19584000a9c80ef5b8dfb187f8f00e4d1e7"
S = "${WORKDIR}/git"

inherit autotools lib_package binconfig pkgconfig

do_unpackpost() {
        QUILT_PATCHES=debian/patches quilt push -a
}

addtask unpackpost after do_unpack before do_patch
