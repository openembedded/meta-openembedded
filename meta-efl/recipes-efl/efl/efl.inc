SUMMARY = "EFL"
LICENSE = "MIT BSD LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=a1809a807e39dd48ddcf7be98477d130"

DEPENDS_class-native = "lua5.1-native freetype-native libxext-native libpng-native jpeg-native tiff-native libfribidi-native glib-2.0-native dbus-native"
DEPENDS = "virtual/libiconv tslib curl glib-2.0 gnutls pkgconfig zlib jpeg openssl libsndfile1 lua5.1 dbus libexif librsvg freetype libpng tiff libxext virtual/libx11 libxrender fontconfig libfribidi giflib udev libxcursor libxcomposite libxinerama libxrandr libxtst libxscrnsaver efl-native"

inherit efl gettext pkgconfig

BBCLASSEXTEND = "native"

PROVIDES += "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus"

# we don't have bullet for physics
EXTRA_OECONF = " \
    --disable-physics \
"
EXTRA_OECONF_append_class-target = " \
    --with-bin-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
"
EXTRA_OECONF_append_class-native = " \
    --disable-fontconfig \
    --disable-audio \
    --with-x11=none \
    --disable-image-loader-bmp \
    --disable-image-loader-eet \
    --disable-image-loader-generic \
    --disable-image-loader-gif \
    --disable-image-loader-ico \
    --disable-image-loader-jp2k \
    --disable-image-loader-pmaps \
    --disable-image-loader-psd \
    --disable-image-loader-tga \
    --disable-image-loader-wbmp \
    --disable-image-loader-webp \
    --disable-image-loader-xpm \
"

PACKAGECONFIG ??= "opengl-es"
PACKAGECONFIG_class-native = ""

# sort of tri-state
PACKAGECONFIG[opengl-full] = "--with-opengl=full,,virtual/libgl"
PACKAGECONFIG[opengl-es] = "--with-opengl=es,,virtual/libgles2"
PACKAGECONFIG[opengl-none] = "--with-opengl=none,,"
# used only in ecore examples and supports only 0.10 now
PACKAGECONFIG[gstreamer] = "--enable-gstreamer,--disable-gstreamer,gstreamer"
PACKAGECONFIG[pulseaudio] = "--enable-pulseaudio,--disable-pulseaudio,pulseaudio"

# new efl doesn't have option to disable eeze build and we don't want to provide udev-native just to build eeze-native we don't need
do_configure_prepend_class-native() {
    sed -i 's/EFL_LIB_START_OPTIONAL(\[Eeze\], \[test "${have_linux}" = "yes"\])/EFL_LIB_START_OPTIONAL([Eeze], [test "${no-we-really-dont-want-native-eeze}" = "yes"])/g' ${S}/configure.ac
}

PACKAGES += "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus"
RPROVIDES_edje += "edje-utils"

python populate_packages_prepend () {
    for plugin_type in "engines loaders savers".split():
        bb.note( "splitting packages for evas %s..." % plugin_type )
        basedir = d.expand( '${libdir}/evas/modules/%s' % plugin_type)

        do_split_packages(d, basedir, '^(.*)',
        output_pattern = 'evas-' + plugin_type[:-1] + "-%s",
        description = 'Evas module %s',
        allow_dirs=True, recursive=False, extra_depends="" )
        
        plugin_type = "cserve2"
        bb.note( "splitting packages for evas %s..." % plugin_type )
        basedir = d.expand( '${libdir}/evas/%s/loaders' % plugin_type)

        do_split_packages(d, basedir, '^(.*)',
        output_pattern = 'evas-' + plugin_type + "-%s",
        description = 'Evas csever2 loader module %s',
        allow_dirs=True, recursive=False, extra_depends="" )
}

PACKAGES_DYNAMIC += "^evas-engine-.* ^evas-loader-.* ^evas-saver-.* ^evas-cserve2-.*"

FILES_efreet = " \
    ${libdir}/efreet/*/efreet*create \
    ${bindir}/efreetd \
    ${datadir}/efreet \
"
FILES_eet = " \
    ${bindir}/eet \
"
FILES_emotion = " \
    ${datadir}/emotion \
"
FILES_embryo = " \
    ${datadir}/embryo \
"
FILES_ethumb = " \
    ${bindir}/ethumbd \
    ${bindir}/ethumb \
    ${libdir}/ethumb/*/*/*/module.so \
    ${libdir}/ethumb/*/*/*/*.edj \
    ${libdir}/ethumb_client/utils/*/ethumbd_slave \
    ${datadir}/ethumb* \
"
FILES_ecore = " \
    ${libdir}/ecore*/*/*/*/module.so \
    ${datadir}/ecore* \
"
FILES_evas = " \
    ${libdir}/evas*/*/*/*/*/module.so \
    ${libdir}/evas*/*/*/*/*/*.edj \
    ${libdir}/evas/cserve2/bin/*/evas_cserve2* \
    ${datadir}/evas* \
"

FILES_eeze = " \
    ${libdir}/eeze*/*/*/*/*/module.so \
    ${datadir}/eeze \
"
FILES_edje = " \
    ${libdir}/edje*/*/*/*/module.so \
    ${libdir}/edje/utils/*/epp \
    ${datadir}/edje \
    ${datadir}/mime \
"
FILES_${PN}-dbg += " \
    ${libdir}/efreet/*/.debug \
    ${libdir}/ecore*/*/*/*/.debug \
    ${libdir}/evas*/*/*/*/*/.debug \
    ${libdir}/evas/cserve2/bin/*/.debug \
    ${libdir}/eeze*/*/*/*/*/.debug \
    ${libdir}/edje*/*/*/*/.debug \
    ${libdir}/edje/utils/*/.debug \
    ${libdir}/ethumb/*/*/*/.debug \
    ${libdir}/ethumb_client/utils/*/.debug \
"
FILES_${PN}-dev += " \
    ${libdir}/cmake \
    ${libdir}/ecore*/*/*/*/module.la \
    ${libdir}/evas*/*/*/*/*/module.la \
    ${libdir}/ethumb*/*/*/*/module.la \
    ${libdir}/eeze*/*/*/*/*/module.la \
    ${libdir}/edje*/*/*/*/module.la \
    ${datadir}/gdb/auto-load \
    ${datadir}/eo/gdb \
    ${bindir}/eldbus-codegen \
    ${bindir}/eina-bench-cmp \
"
