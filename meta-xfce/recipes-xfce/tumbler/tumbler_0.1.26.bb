DESCRIPTION="Thumbnail service implementing the thumbnail management D-Bus specification"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "dbus-glib freetype gdk-pixbuf poppler"

inherit xfce

SRC_URI[md5sum] = "874621e7820b3bfe0e8ab66d17f1dc31"
SRC_URI[sha256sum] = "6602ce700160d09edd88ea0b9bcb16abbc28974f372e646709e0ed8fbacc8a78"

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
