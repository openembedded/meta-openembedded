DESCRIPTION="Thumbnail service implementing the thumbnail management D-Bus specification"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "dbus-glib freetype gdk-pixbuf poppler"

inherit xfce

SRC_URI[md5sum] = "d4edc15c172714c7a3eaf3c719b8faf6"
SRC_URI[sha256sum] = "02d5de508d8c18692a25a832a9280c2043f6b7c90dd3fcbd87f5955337015546"

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
