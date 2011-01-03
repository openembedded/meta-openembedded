DESCRIPTION = "DirectFB is a thin library that provides developers \
with hardware graphics acceleration, input device handling and \
abstraction, an integrated windowing system with support for \
translucent windows and multiple display layers on top of the \
Linux framebuffer device."
SECTION = "libs"
LICENSE = "LGPL"
HOMEPAGE = "http://directfb.org"
DEPENDS = "jpeg libpng freetype zlib tslib"

SRC_URI = " \
    http://www.directfb.org/downloads/Old/DirectFB-${PV}.tar.gz \
    file://fix-pkgconfig-cflags.patch \
    file://fix-font-missing-char.patch \
    file://getpagesize.patch \
    file://mkdfiff.patch \
    file://dont-use-linux-config.patch \
    file://ts_lib_autotools.patch \
"
S = "${WORKDIR}/DirectFB-${PV}"

LDFLAGS_append =" -lts -lm"

inherit autotools binconfig pkgconfig

EXTRA_OECONF = "\
  --with-gfxdrivers=none \
  --enable-libmpeg3=no \
  --enable-freetype=yes \
  --enable-sdl=no \
  --enable-vnc=no \
  --disable-x11 \
"

do_install() {
        oe_runmake 'DESTDIR=${D}' install
}


#PACKAGES_DYNAMIC = "directfb-inputdrivers-*"
#
#python populate_packages_prepend () {
#    import os.path
#    inputdrivers_libdir = bb.data.expand('${libdir}/directfb-${RV}/inputdrivers', d)
#    do_split_packages(d, inputdrivers_libdir, '*.so$', 'directfb-inputdrivers-%s', 'Directfb plugin for %s')
#}

# NOTE: monolithic packaging for now, should improve that eventually



FILES_${PN}-dbg += "\
  ${libdir}/directfb-${RV}/*/*/.debug/*.so \
  ${libdir}/directfb-${RV}/*/.debug/*.so \
"

FILES_${PN}-dev += "\
  ${bindir}/directfb-config \
  ${libdir}/directfb-${RV}/systems/*.la \
  ${libdir}/directfb-${RV}/inputdrivers/*.la \
  ${libdir}/directfb-${RV}/interfaces/*/*.la \
  ${libdir}/directfb-${RV}/wm/*.la \
"

FILES_${PN} += "\
  ${libdir}/directfb-${RV}/systems/*.so \
  ${libdir}/directfb-${RV}/inputdrivers/*.so \
  ${libdir}/directfb-${RV}/interfaces/*/*.so \
  ${libdir}/directfb-${RV}/wm/*.so \
  ${datadir}/directfb-${PV} \
"
