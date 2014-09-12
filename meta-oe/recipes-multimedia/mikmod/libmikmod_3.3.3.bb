DESCRIPTION = "libmikmod is a module player library supporting many formats, including mod, s3m, it, and xm."
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=b2b941d484c442e5c031a51463d8e11b"

DEPENDS = "alsa-lib texinfo"

SRC_URI = "\
    ${SOURCEFORGE_MIRROR}/project/mikmod/outdated_versions/${BPN}/${PV}/${BPN}-${PV}.tar.gz \
"
SRC_URI[md5sum] = "0e0f9bce8f8e598ca292b41e0ae385c8"
SRC_URI[sha256sum] = "79f02478c5abd8b2af73df4cc5f9d52625aa044327c01563168e270cf79b2437"

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

