require ${BPN}.inc

DEPENDS = "alsa-lib zlib jpeg libpng libxext libxft libxrender"

inherit features_check binconfig lib_package gtk-icon-cache mime mime-xdg
REQUIRED_DISTRO_FEATURES = "x11"

SRC_URI += "file://0003-fluid-CMakeLists.txt-Do-not-export-fluid-target.patch"

EXTRA_OECMAKE += " \
    -DFLTK_BUILD_SHARED_LIBS=ON \
    -DFLTK_USE_PTHREADS=ON \
    -DFLTK_USE_XFT=ON \
"

# The Wayland backend builds a hybrid Wayland/X11 library, which needs all optional X11 extensions
PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'opengl wayland', d)} \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'xinerama xfixes xcursor', '', d)}"

PACKAGECONFIG[cairo] = "-DFLTK_OPTION_CAIRO_WINDOW=ON,-DFLTK_OPTION_CAIRO_WINDOW=OFF,cairo"
FLTK_GL_DEPS = "virtual/libgl libglu ${@bb.utils.contains('PACKAGECONFIG', 'wayland', 'virtual/egl', '', d)}"
PACKAGECONFIG[opengl] = "-DFLTK_BUILD_GL=ON,-DFLTK_BUILD_GL=OFF,${FLTK_GL_DEPS}"
PACKAGECONFIG[wayland] = "-DFLTK_BACKEND_WAYLAND=ON -DFLTK_USE_SYSTEM_LIBDECOR=ON,-DFLTK_BACKEND_WAYLAND=OFF,wayland wayland-native wayland-protocols libxkbcommon libdecor cairo pango dbus"
PACKAGECONFIG[xinerama] = "-DFLTK_USE_XINERAMA=ON,-DFLTK_USE_XINERAMA=OFF,libxinerama"
PACKAGECONFIG[xfixes] = "-DFLTK_USE_XFIXES=ON,-DFLTK_USE_XFIXES=OFF,libxfixes"
PACKAGECONFIG[xcursor] = "-DFLTK_USE_XCURSOR=ON,-DFLTK_USE_XCURSOR=OFF,libxcursor"

do_install:append() {
    sed -i -e 's,${TMPDIR},,g' ${D}${bindir}/fltk-config ${D}${datadir}/fltk/*.cmake
}

python populate_packages:prepend () {
    if (d.getVar('DEBIAN_NAMES')):
        d.setVar('PKG:${BPN}', 'libfltk${PV}')
}

LEAD_SONAME = "libfltk.so"

# .desktop / icons / mime only necessary for fluid app
FILES:${PN}-bin += " \
    ${datadir}/applications \
    ${datadir}/icons \
    ${datadir}/mime \
"

# cmake files
FILES:${PN}-dev += "${datadir}/fltk"
