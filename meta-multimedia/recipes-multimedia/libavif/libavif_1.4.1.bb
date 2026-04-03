SUMMARY = "This library aims to be a friendly, portable C implementation of the AV1 Image File Format"
HOMEPAGE = "https://github.com/AOMediaCodec/libavif"
SECTION = "libs"
# Most is the code is under BSD-2, but libyuv is under BSD-3, and iccjpeg is under IJG
LICENSE = "BSD-2-Clause & BSD-3-Clause & IJG"
LIC_FILES_CHKSUM = "file://LICENSE;md5=51549db0941829faeedcc86efec2f4c0"

SRC_URI = "git://github.com/AOMediaCodec/libavif.git;protocol=https;branch=main;tag=v${PV}"
SRCREV = "6543b22b5bc706c53f038a16fe515f921556d9b3"

DEPENDS = "nasm-native"

inherit cmake pkgconfig

EXTRA_OECMAKE += " \
    -DAVIF_BUILD_APPS=OFF \
    -DAVIF_BUILD_MAN_PAGES=OFF \
    -DAVIF_CODEC_RAV1E=OFF \
    -DAVIF_LIBXML2=OFF \
    -DAVIF_BUILD_GDK_PIXBUF=OFF \
    -DAVIF_LIBYUV=OFF \
"

PACKAGECONFIG ?= "dav1d"
PACKAGECONFIG[aom] = "-DAVIF_CODEC_AOM=SYSTEM,-DAVIF_CODEC_AOM=OFF,aom"
PACKAGECONFIG[dav1d] = "-DAVIF_CODEC_DAV1D=SYSTEM,-DAVIF_CODEC_DAV1D=OFF,dav1d"
PACKAGECONFIG[svt] = "-DAVIF_CODEC_SVT=SYSTEM,-DAVIF_CODEC_SVT=OFF,svt-av1"
