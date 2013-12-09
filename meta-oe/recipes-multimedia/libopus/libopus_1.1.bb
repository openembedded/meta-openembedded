SUMMARY = "Opus Audio Codec"
DESCRIPTION = "The Opus codec is designed for interactive \
speech and audio transmission over the Internet. It is \
designed by the IETF Codec Working Group and incorporates \
technology from Skype's SILK codec and Xiph.Org's CELT codec."
HOMEPAGE = "http://www.opus-codec.org/"
SECTION = "libs/multimedia"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=e304cdf74c2a1b0a33a5084c128a23a3"

SRC_URI = "http://downloads.xiph.org/releases/opus/opus-${PV}.tar.gz"
SRC_URI[md5sum] = "c5a8cf7c0b066759542bc4ca46817ac6"
SRC_URI[sha256sum] = "b9727015a58affcf3db527322bf8c4d2fcf39f5f6b8f15dbceca20206cbe1d95"

S = "${WORKDIR}/opus-${PV}"

inherit autotools pkgconfig

require libopus-fpu.inc
EXTRA_OECONF = "${@get_libopus_fpu_setting(bb, d)}"
