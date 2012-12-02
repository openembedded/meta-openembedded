DESCRIPTION = "Fluidsynth is a software synthesizer"
HOMEPAGE = "http://www.fluidsynth.org/"
SECTION = "libs/multimedia"
LICENSE = "LGPLv-2.0+"
LIC_FILES_CHKSUM = "file://COPYING;md5=dda26baa823649da9a68947707650272"

DEPENDS = "alsa-lib ncurses"

SRC_URI = "http://savannah.nongnu.org/download/fluid/${P}.tar.gz"
SRC_URI[md5sum] = "e2abfd2e69fd8b28d965df968d7d44ee"
SRC_URI[sha256sum] = "45e7c9967d0fb0344f4da539ab343fb979384b36a429a8594c94cf466dff4320"

inherit autotools pkgconfig lib_package

#Has broken libtool usage
do_configure() {
	gnu-configize
	oe_runconf
}
