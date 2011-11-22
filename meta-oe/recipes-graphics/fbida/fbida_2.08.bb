HOMEPAGE = "http://linux.bytesex.org/fbida/"
DESCRIPTION = "frame buffer image and doc viewer tools"
AUTHOR = "Gerd Hoffmann"
SECTION = "utils"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

DEPENDS = "virtual/libiconv libpng jpeg fontconfig freetype libexif"

SRC_URI = "http://dl.bytesex.org/releases/fbida/fbida-${PV}.tar.gz"
SRC_URI[md5sum] = "9b3693ab26a58194e36b479bffb61ed0"
SRC_URI[sha256sum] = "298e7f3545e33596a5876f6b9c3da7ef475c2692e5fab0df824fbcd7a489cd80"

EXTRA_OEMAKE = "STRIP="

do_compile() {
	oe_runmake
}

do_install() {
	oe_runmake 'DESTDIR=${D}' install
}

RDEPENDS_${PN} = "ttf-dejavu-sans-mono"

