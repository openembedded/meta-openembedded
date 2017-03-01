SUMMARY = "FLTK is a cross-platform C++ GUI toolkit"
HOMEPAGE = "http://www.fltk.org"
SECTION = "libs"
LICENSE = "LGPLv2 & FLTK"
LIC_FILES_CHKSUM = "file://COPYING;md5=f6b26344a24a941a01a5b0826e80b5ca"

DEPENDS = "alsa-lib zlib jpeg libpng libxext libxft"

SRC_URI = " \
    http://fltk.org/pub/fltk/1.3.4/${BP}-source.tar.gz \
    file://disable_test.patch \
    file://fltk-no-freetype-config.patch \
"

SRC_URI[md5sum] = "d7fcd27ab928648e1a1366dd2e273970"
SRC_URI[sha256sum] = "7fb2c8882433ce694e6900c94fda505e8f4ed3fa9c7e597007098a33b85c53f4"

inherit autotools-brokensep binconfig pkgconfig lib_package

EXTRA_AUTORECONF = "--exclude=autopoint,autoheader"

TARGET_CC_ARCH += "${LDFLAGS} -DXFT_MAJOR=2"

EXTRA_OECONF = "--enable-shared \
                --enable-threads \
                --enable-xdbe \
                --enable-xft \
"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'opengl', d)}"

PACKAGECONFIG[opengl] = "--enable-gl,--disable-gl,virtual/libgl"
PACKAGECONFIG[xinerama] = "--enable-xinerama,--disable-xinerama,libxinerama"
PACKAGECONFIG[xfixes] = "--enable-xfixes,--disable-xfixes,libxfixes"
PACKAGECONFIG[xcursor] = "--enable-xcursor,--disable-xcursor,libxcursor"

do_install_append_class-target() {
    sed -i -e 's,${STAGING_DIR_HOST},,g' ${D}${bindir}/fltk-config
}

python populate_packages_prepend () {
    if (d.getVar('DEBIAN_NAMES')):
        d.setVar('PKG_${BPN}', 'libfltk${PV}')
}

LEAD_SONAME = "libfltk.so"

