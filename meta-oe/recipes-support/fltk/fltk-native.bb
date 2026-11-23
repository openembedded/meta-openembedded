require ${BPN}.inc

DEPENDS = "zlib-native jpeg-native libpng-native libxext-native libxft-native"

inherit native

EXTRA_OECMAKE += " \
    -DFLTK_BACKEND_WAYLAND=OFF \
    -DFLTK_BUILD_SHARED_LIBS=OFF \
    -DFLTK_BUILD_GL=OFF \
    -DFLTK_USE_PTHREADS=OFF \
    -DFLTK_USE_XFT=OFF \
    -DFLTK_USE_XINERAMA=OFF \
    -DFLTK_USE_XFIXES=OFF \
    -DFLTK_USE_XCURSOR=OFF \
    -DFLTK_USE_XRENDER=OFF \
    -DHAVE_TRUNC=1 \
"

do_install:append() {
    # make sure native fltk-config is not used accidentaly
    rm -f ${D}${bindir}/fltk-config
}
