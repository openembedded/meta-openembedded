DESCRIPTION = "DVD access multimeda library"
SECTION = "libs/multimedia"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
SRC_URI = "git://git.debian-maintainers.org/git/daniel/${PN}.git;protocol=git;branch=debian"

SRCREV = "e465b500c12be5b4c0b6918a9b98038611f66c57"
S = "${WORKDIR}/git"

inherit autotools lib_package binconfig pkgconfig

do_unpackpost() {
        QUILT_PATCHES=debian/patches quilt push -a
}

addtask unpackpost after do_unpack before do_patch
