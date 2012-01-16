SECTION = "x11/gnome/libs"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://COPYING.LIB;md5=7fbc338309ac38fefcd64b04bb903e34"

inherit gnome pkgconfig

SRC_URI[archive.md5sum] = "3a754b1df0a4d5fd4836a05020a0bb4a"
SRC_URI[archive.sha256sum] = "0be51ee3069a2ef21d98561ee28036dd263ac08b401776fe9164e084825ffd84"

DEPENDS = "libgnomecanvas libbonobo libgnome glib-2.0 gtk-doc gconf libxml2 libglade"

EXTRA_OECONF = "--disable-gtk-doc"

FILES_${PN} += "${libdir}/libglade/2.0/*.so"
FILES_${PN}-dev += "${libdir}/libglade/2.0/* ${datadir}/gnome-2.0/ui \
                    ${libdir}/bonobo-2.0/samples"
FILES_${PN}-dbg += "${libdir}/bonobo-2.0/samples/.debug \
                    ${libdir}/libglade/2.0/.debug"
