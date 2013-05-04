DESCRIPTION = "Fluidsynth is a software synthesizer"
HOMEPAGE = "http://www.fluidsynth.org/"
SECTION = "libs/multimedia"
LICENSE = "LGPLv-2.0+"
LIC_FILES_CHKSUM = "file://COPYING;md5=dda26baa823649da9a68947707650272"

DEPENDS = "alsa-lib ncurses glib-2.0"

SRC_URI = "http://savannah.nongnu.org/download/fluid/${P}.tar.gz"
SRC_URI[md5sum] = "0db3da78028d255026230809c6e21b44"
SRC_URI[sha256sum] = "55638cb04f39f4df76c081e22ca7feeea58ddd20ebf779a7db2d38b1ff374cd6"

inherit autotools pkgconfig lib_package
