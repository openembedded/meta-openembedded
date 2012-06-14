SUMMARY = "Opus Audio Codec"
DESCRIPTION = "The Opus codec is designed for interactive \
speech and audio transmission over the Internet. It is \
designed by the IETF Codec Working Group and incorporates \
technology from Skype's SILK codec and Xiph.Org's CELT codec."
HOMEPAGE = "http://www.opus-codec.org/"
SECTION = "libs/multimedia"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=6d9f9b3714db6bbcfb6e42170d9c664d"

SRC_URI = "http://downloads.xiph.org/releases/opus/opus-${PV}.tar.gz"
SRC_URI[md5sum] = "c7161b247a8437ae6b0f11dd872e69e8"
SRC_URI[sha256sum] = "b1cad6846a8f819a141009fe3f8f10c946e8eff7e9c2339cd517bb136cc59eae"

S = "${WORKDIR}/opus-${PV}"

inherit autotools pkgconfig

require libopus-fpu.inc
EXTRA_OECONF = "${@get_libopus_fpu_setting(bb, d)}"
