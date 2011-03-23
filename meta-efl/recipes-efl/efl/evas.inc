DESCRIPTION = "Evas is the Enlightenment canvas API"
LICENSE = "MIT BSD"
# can also depend on valgrind, libsdl-x11, directfb
DEPENDS = "librsvg eina eet freetype jpeg libpng virtual/libx11 libxext libxrender fontconfig libfribidi giflib"
DEPENDS_virtclass-native = "freetype-native libxext-native libpng-native jpeg-native eet-native eina-native libfribidi-native"

inherit efl

BBCLASSEXTEND = "native"
INC_PR = "r1"
FILESPATHPKG =. "${BPN}-${PV}:${BPN}:"

python populate_packages_prepend () {
        for plugin_type in "engines loaders savers".split():
            bb.note( "splitting packages for evas %s..." % plugin_type )
            basedir = bb.data.expand( '${libdir}/evas/modules/%s' % plugin_type, d )

            do_split_packages(d, basedir, '^(.*)',
            output_pattern = 'evas-' + plugin_type[:-1] + "-%s",
            description = 'Evas module %s',
            allow_dirs=True, recursive=False, extra_depends="" )
}

do_install_append() {
    find "${D}" -name .debug -type d -exec rm -rf {} \;
}

FILES_${PN}-dev += "${libdir}/evas/modules/*/*/*/*.a ${libdir}/evas/modules/*/*/*/*.la"
FILES_${PN}-dbg += "${libdir}/evas/modules/*/*/*/.debug/"

PACKAGES_DYNAMIC_virtclass-native = ""
PACKAGES_DYNAMIC = "evas-engine-* evas-loader-* evas-saver-*"

RRECOMMENDS_${PN} = " \
  evas-engine-buffer \
  evas-engine-fb \
  evas-engine-software-generic \
  evas-engine-software-x11 \
  evas-engine-software-16 \
  evas-engine-software-16-x11 \
  evas-engine-xrender-x11 \
 \
  evas-loader-png \
  evas-loader-jpeg \
  evas-loader-eet \
  evas-loader-gif \
"

# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""

RREPLACES_${PN} = "libevas-ver-pre-svn-00-0 libevas-ver-pre-svn-01-0"

RREPLACES_${PN}-tests = "libevas-ver-pre-svn-00-tests libevas-ver-pre-svn-01-tests"

RREPLACES_evas-loader-pmaps = "liblibevas-ver-pre-svn-00-loader-pmaps libevas-ver-pre-svn-00-loader-pmaps "
RREPLACES_evas-loader-eet = "liblibevas-ver-pre-svn-00-loader-eet libevas-ver-pre-svn-00-loader-eet "
RREPLACES_evas-loader-jpeg = "liblibevas-ver-pre-svn-00-loader-jpeg libevas-ver-pre-svn-00-loader-jpeg "
RREPLACES_evas-loader-tiff = "liblibevas-ver-pre-svn-00-loader-tiff libevas-ver-pre-svn-00-loader-tiff "
RREPLACES_evas-loader-png = "liblibevas-ver-pre-svn-00-loader-png libevas-ver-pre-svn-00-loader-png "
RREPLACES_evas-loader-xpm = "liblibevas-ver-pre-svn-00-loader-xpm libevas-ver-pre-svn-00-loader-xpm "

RREPLACES_evas-saver-pmaps = "liblibevas-ver-pre-svn-00-saver-pmaps libevas-ver-pre-svn-00-saver-pmaps "
RREPLACES_evas-saver-eet = "liblibevas-ver-pre-svn-00-saver-eet libevas-ver-pre-svn-00-saver-eet "
RREPLACES_evas-saver-jpeg = "liblibevas-ver-pre-svn-00-saver-jpeg libevas-ver-pre-svn-00-saver-jpeg "
RREPLACES_evas-saver-tiff = "liblibevas-ver-pre-svn-00-saver-tiff libevas-ver-pre-svn-00-saver-tiff "
RREPLACES_evas-saver-png = "liblibevas-ver-pre-svn-00-saver-png libevas-ver-pre-svn-00-saver-png "
RREPLACES_evas-saver-xpm = "liblibevas-ver-pre-svn-00-saver-xpm libevas-ver-pre-svn-00-saver-xpm "

RREPLACES_evas-engine-fb = "liblibevas-ver-pre-svn-00-engine-fb libevas-ver-pre-svn-00-engine-fb "
RREPLACES_evas-engine-software-generic = "liblibevas-ver-pre-svn-00-engine-software-generic libevas-ver-pre-svn-00-engine-software-generic "
RREPLACES_evas-engine-software-16 = "liblibevas-ver-pre-svn-00-engine-software-16 libevas-ver-pre-svn-00-engine-software-16 "
RREPLACES_evas-engine-buffer = "liblibevas-ver-pre-svn-00-engine-buffer libevas-ver-pre-svn-00-engine-buffer "
RREPLACES_evas-engine-xrender-x11 = "liblibevas-ver-pre-svn-00-engine-xrender-x11 libevas-ver-pre-svn-00-engine-xrender-x11 "
RREPLACES_evas-engine-software-x11 = "liblibevas-ver-pre-svn-00-engine-software-x11 libevas-ver-pre-svn-00-engine-software-x11 "

# disabling this, since (due to OE bugs) it drags in whole Gtk+
#RRECOMMENDS_${PN} += " \
#  libevas-loader-svg \
#"

EVAS_CPU_TWEAKS = ""
# Disabled for now, see http://thread.gmane.org/gmane.comp.window-managers.enlightenment.devel/25194
EVAS_CPU_TWEAKS_armv7a = "--disable-cpu-neon"

# common options
EVAS_OECONF = " \
                --x-includes=${STAGING_INCDIR}/X11 \
                --x-libraries=${STAGING_LIBDIR} \
                --enable-evas-magic-debug \
                --enable-fb \
                --disable-directfb \
                --disable-sdl \
                --enable-buffer \
                --disable-software-ddraw \
                --disable-software-qtopia \
                --disable-software-xcb \
                --disable-xrender-xcb \
                --disable-glitz-x11 \
                --enable-image-loader-eet \
                --disable-image-loader-edb \
                --enable-image-loader-png \
                --enable-image-loader-jpeg \
                --enable-image-loader-tiff \
                --enable-image-loader-xpm \
                --enable-cpu-c \
                --enable-fontconfig \
                --enable-font-loader-eet \
                --enable-scale-sample \
                --enable-scale-smooth \
                --enable-convert-yuv \
                --enable-small-dither-mask \
                --disable-no-dither-mask \
                --disable-convert-8-rgb-332 \
                --disable-convert-8-rgb-666 \
                --disable-convert-8-rgb-232 \
                --disable-convert-8-rgb-222 \
                --disable-convert-8-rgb-221 \
                --disable-convert-8-rgb-121 \
                --disable-convert-8-rgb-111 \
                --enable-convert-16-rgb-565 \
                --disable-convert-16-rgb-555 \
                --disable-convert-16-rgb-444 \
                --disable-convert-16-rgb-ipq \
                --enable-convert-16-rgb-rot-0 \
                --enable-convert-16-rgb-rot-90 \
                --disable-convert-16-rgb-rot-180 \
                --enable-convert-16-rgb-rot-270 \
                ${EVAS_CPU_TWEAKS} \
"

# either sgx or 6410 atm
GLES ?= "sgx"

# This is a hack to get openGL|ES 2.x support enabled for people that have the SDK headers in staging.
# We put this in the main recipe, since it will just not build the gl stuff when the headers are missing

# If the above sentence confuse you: everything is built and configured as before if you don't have the SDK

EXTRA_OECONF = "${EVAS_OECONF} \
                --enable-pthreads \
                --enable-async-events \
                --enable-async-preload \
                --enable-simple-x11 \
                --enable-software-x11 \
                --enable-xrender-x11 \
                --enable-software-16-x11 \
                --enable-image-loader-gif \
                --enable-image-loader-svg \
                --enable-convert-24-rgb-888 \
                --enable-convert-24-bgr-888 \
                --enable-convert-32-rgb-8888 \
                --enable-convert-32-rgbx-8888 \
                --enable-convert-32-bgr-8888 \
                --enable-convert-32-bgrx-8888 \
                --enable-convert-32-rgb-rot-0 \
                --enable-convert-32-rgb-rot-90 \
                --disable-convert-32-rgb-rot-180 \
                --enable-convert-32-rgb-rot-270 \
                --enable-gl-x11 \
                --enable-gl-flavor-gles \
                --enable-gles-variety-${GLES} \
"
EXTRA_OECONF_virtclass-native = "${EVAS_OECONF} \
                --disable-software-x11 \
                --disable-software-16-x11 \
                --disable-gl-x11 \
                --disable-xrender-x11 \
                --disable-image-loader-gif \
                --disable-image-loader-svg \
                --disable-convert-24-rgb-888 \
                --disable-convert-24-bgr-888 \
                --disable-convert-32-rgb-8888 \
                --disable-convert-32-rgbx-8888 \
                --disable-convert-32-bgr-8888 \
                --disable-convert-32-bgrx-8888 \
                --disable-convert-32-rgb-rot-0 \
                --disable-convert-32-rgb-rot-90 \
                --disable-convert-32-rgb-rot-270 \
"
