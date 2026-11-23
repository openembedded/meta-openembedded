SUMMARY = "FLTK 1.3 compatibility library"
DESCRIPTION = "FLTK 1.3 libraries for applications that do not support FLTK 1.4 yet. \
They install side by side with fltk: headers in ${includedir}/fltk1.3, link \
libraries in ${libdir}/fltk1.3, CMake files in ${datadir}/fltk1.3 and \
fltk-config1.3."
HOMEPAGE = "https://www.fltk.org"
SECTION = "libs"
LICENSE = "LGPL-2.0-only AND LicenseRef-FLTK"
LIC_FILES_CHKSUM = "file://COPYING;md5=f6b26344a24a941a01a5b0826e80b5ca"

DEPENDS = "alsa-lib zlib jpeg libpng libxext libxft"

SRC_URI = " \
    https://github.com/fltk/fltk/releases/download/release-${PV}/fltk-${PV}-source.tar.bz2 \
    file://0001-Fl_Preferences.cxx-do-not-use-dlopen-in-case-glibc-s.patch \
"
SRC_URI[sha256sum] = "ca2e144e5f89173cd094cc273940d56230b1bf613083a0792e6406dc191cd99f"

S = "${UNPACKDIR}/fltk-${PV}"

UPSTREAM_CHECK_URI = "https://www.fltk.org/software.php"
UPSTREAM_CHECK_REGEX = "fltk-(?P<pver>1\.3(\.\d+)+)-source\.tar"

inherit cmake pkgconfig features_check binconfig
REQUIRED_DISTRO_FEATURES = "x11"

BINCONFIG_GLOB = "fltk-config1.3"

TARGET_CC_ARCH += "${LDFLAGS} -DXFT_MAJOR=2"

FLTK13_RELLIBDIR = "${@os.path.relpath(d.getVar('libdir'), d.getVar('prefix') + '/')}/fltk1.3"
FLTK13_RELINCDIR = "${@os.path.relpath(d.getVar('includedir'), d.getVar('prefix') + '/')}/fltk1.3"

# fluid is not built when cross-compiling, so nothing clashes in ${bindir} or ${datadir}
EXTRA_OECMAKE = " \
    -DOPTION_BUILD_SHARED_LIBS=ON \
    -DOPTION_USE_THREADS=ON \
    -DFLTK_BUILD_TEST=OFF \
    -DOPTION_USE_XDBE=ON \
    -DOPTION_USE_XFT=ON \
    -DOPTION_BUILD_HTML_DOCUMENTATION=OFF \
    -DOPTION_BUILD_PDF_DOCUMENTATION=OFF \
    -DCMAKE_INSTALL_LIBDIR=${FLTK13_RELLIBDIR} \
    -DCMAKE_INSTALL_INCLUDEDIR=${FLTK13_RELINCDIR} \
"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'opengl', d)}"

PACKAGECONFIG[cairo] = "-DOPTION_CAIRO=ON,-DOPTION_CAIRO=OFF,cairo"
PACKAGECONFIG[opengl] = "-DOPTION_USE_GL=ON,-DOPTION_USE_GL=OFF,virtual/libgl"
PACKAGECONFIG[xinerama] = "-DOPTION_USE_XINERAMA=ON,-DOPTION_USE_XINERAMA=OFF,libxinerama"
PACKAGECONFIG[xfixes] = "-DOPTION_USE_XFIXES=ON,-DOPTION_USE_XFIXES=OFF,libxfixes"
PACKAGECONFIG[xcursor] = "-DOPTION_USE_XCURSOR=ON,-DOPTION_USE_XCURSOR=OFF,libxcursor"

do_install:append() {
    # Runtime libraries go to ${libdir} for the loader; the link symlinks and static libraries stay in fltk1.3/
    mv ${D}${libdir}/fltk1.3/lib*.so.1.3* ${D}${libdir}/
    for lib in ${D}${libdir}/fltk1.3/lib*.so; do
        name=$(basename $lib)
        ln -sf ../$name.1.3 $lib
        ln -sf ../$name.1.3 $lib.1.3
        ln -sf ../$name.${PV} $lib.${PV}
    done

    mv ${D}${bindir}/fltk-config ${D}${bindir}/fltk-config1.3
    mv ${D}${datadir}/fltk ${D}${datadir}/fltk1.3
    sed -i -e 's,"${prefix}/include","${includedir}/fltk1.3",' ${D}${datadir}/fltk1.3/FLTKConfig.cmake
    sed -i -e 's,${TMPDIR},,g' ${D}${bindir}/fltk-config1.3 ${D}${datadir}/fltk1.3/*.cmake

    # Manual pages are the same as those of fltk
    rm -rf ${D}${mandir}
}

FILES:${PN}-staticdev += "${libdir}/fltk1.3/*.a"
FILES:${PN}-dev += " \
    ${bindir}/fltk-config1.3 \
    ${libdir}/fltk1.3 \
    ${datadir}/fltk1.3 \
"

# Keep the package names apart from those of fltk
AUTO_LIBNAME_PKGS = ""
