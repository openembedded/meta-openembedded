DESCRIPTION="Thumbnail service implementing the thumbnail management D-Bus specification"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "dbus-glib freetype gdk-pixbuf poppler curl xfce4-dev-tools-native libxml2 libgsf"

inherit xfce gtk-doc

SRC_URI[md5sum] = "2524e39439c13238565160da0b6fed2d"
SRC_URI[sha256sum] = "e7c20d79c830465f8b3b792893f05e8b8d5ba90aec4973e7517e07ef31537304"
SRC_URI += "file://0001-configure-use-pkg-config-for-freetype2.patch"

INSANE_SKIP_${PN} = "dev-so"

do_install_append() {
    # correct tumbler-cache-plugin.so link (see plugins/xdg-cache/Makefile.am)
    ln -sf ${libdir}/tumbler-1/plugins/cache/tumbler-xdg-cache.so ${D}${libdir}/tumbler-1/plugins/cache/tumbler-cache-plugin.so
}

PACKAGECONFIG ??= ""
PACKAGECONFIG[gstreamer-thumbnailer] = "--enable-gstreamer-thumbnailer,--disable-gstreamer-thumbnailer,gstreamer1.0 gstreamer1.0-plugins-base"

FILES_${PN} += "${datadir}/dbus-1/services \
                ${libdir}/tumbler-1/tumblerd \
                ${libdir}/tumbler-1/plugins/*.so \
                ${libdir}/tumbler-1/plugins/cache/*.so \
"

FILES_${PN}-dev += "${libdir}/tumbler-1/plugins/*.la \
                    ${libdir}/tumbler-1/plugins/cache/*.la"
FILES_${PN}-staticdev += "${libdir}/tumbler-1/plugins/*.a \
                          ${libdir}/tumbler-1/plugins/cache/*.a"
FILES_${PN}-dbg += "${libdir}/tumbler-1/.debug \
                    ${libdir}/tumbler-1/plugins/.debug \
                    ${libdir}/tumbler-1/plugins/cache/.debug \
"
