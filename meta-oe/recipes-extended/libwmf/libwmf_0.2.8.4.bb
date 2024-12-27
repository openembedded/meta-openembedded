SUMMARY = "Library for converting WMF files"
#HOMEPAGE = "http://wvware.sourceforge.net/libwmf.html"
HOMEPAGE = "https://github.com/caolanm/libwmf"
SECTION = "libs"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"


DEPENDS:class-native = "freetype-native libpng-native jpeg-native"
DEPENDS = "freetype libpng jpeg expat gtk+"

BBCLASSEXTEND = "native"

inherit features_check autotools pkgconfig

REQUIRED_DISTRO_FEATURES = "x11"

SRC_URI = "git://github.com/caolanm/libwmf.git;protocol=https;branch=master \
           file://libwmf-0.2.8.4-intoverflow.patch \
           file://libwmf-0.2.8.4-useafterfree.patch \
           file://0001-configure-use-pkg-config-for-freetype.patch \
          "
SRCREV = "27b4aaf8cf653b4cd2ebe14717ffa9e76560485e"

S = "${WORKDIR}/git"

do_install:append() {
    sed -i -e 's@${RECIPE_SYSROOT}@@g' ${D}${bindir}/libwmf-config
}

FILES:${PN}-dbg += "${libdir}/gtk-2.0/2.10.0/loaders/.debug"
FILES:${PN}-dev += "${libdir}/gtk-2.0/2.10.0/loaders/*.la"
FILES:${PN}-staticdev += "${libdir}/gtk-2.0/2.10.0/loaders/*.a"
FILES:${PN} += "${libdir}/gtk-2.0/2.10.0/loaders/*.so"

