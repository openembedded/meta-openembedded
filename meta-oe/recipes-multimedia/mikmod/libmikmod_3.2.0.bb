DESCRIPTION = "libmikmod is a module player library supporting many formats, including mod, s3m, it, and xm."
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=b2b941d484c442e5c031a51463d8e11b"

DEPENDS = "alsa-lib texinfo"

SRC_URI = "\
    http://mikmod.shlomifish.org/files/${P}.tar.gz \
    file://m4.patch \
    file://autofoo.patch \
    file://ldflags.patch \
    file://obsolete_automake_macros.patch \
"

SRC_URI[md5sum] = "96e9820d72a41fe27ff304071739696c"
SRC_URI[sha256sum] = "734c8490bbf9b0c587920b92414dcfa3c2267838a0cdf698d5f1fb6bba8f661e"

inherit autotools binconfig lib_package

EXTRA_OECONF = "\
    --disable-af \
    --enable-alsa \
    --disable-esd \
    --enable-oss \
    --disable-sam9407 \
    --disable-ultra \
    --disable-esdtest \
    --enable-threads \
"

