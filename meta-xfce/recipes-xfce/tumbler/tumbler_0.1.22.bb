DESCRIPTION="Thumbnail service implementing the thumbnail management D-Bus specification"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "dbus-glib freetype gdk-pixbuf poppler"

inherit xfce

INSANE_SKIP_${PN} = "dev-so"

do_install_append() {
    # correct tumbler-cache-plugin.so link (see plugins/xdg-cache/Makefile.am)
    ln -sf ${libdir}/tumbler-1/plugins/cache/tumbler-xdg-cache.so ${D}${libdir}/tumbler-1/plugins/cache/tumbler-cache-plugin.so
}

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

SRC_URI[md5sum] = "e7ba3b8f21f8f7e736f1e214906b4ca9"
SRC_URI[sha256sum] = "9e41fe8f76f9422fb4f804e9635f6734552f0d82361f9971c0442a2119d451b6"
