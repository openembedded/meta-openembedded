SUMMARY = "Opus Audio Codec"
DESCRIPTION = "The Opus codec is designed for interactive \
speech and audio transmission over the Internet. It is \
designed by the IETF Codec Working Group and incorporates \
technology from Skype's SILK codec and Xiph.Org's CELT codec."
HOMEPAGE = "http://www.opus-codec.org/"
SECTION = "libs/multimedia"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=225b2f817ea798b7c705fe1214694891"

SRC_URI = "http://downloads.xiph.org/releases/opus/opus-${PV}.tar.gz"
SRC_URI[md5sum] = "86eedbd3c5a0171d2437850435e6edff"
SRC_URI[sha256sum] = "191a089c92dbc403de6980463dd3604b65beb12d283c607e246c8076363cb49c"

S = "${WORKDIR}/opus-${PV}"

inherit autotools pkgconfig

require libopus-fpu.inc
EXTRA_OECONF = "${@get_libopus_fpu_setting(bb, d)}"
