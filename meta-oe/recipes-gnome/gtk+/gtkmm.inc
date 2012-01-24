DESCRIPTION = "C++ bindings for the GTK+ toolkit."
HOMEPAGE = "http://www.gtkmm.org/"
SECTION = "libs"

LICENSE = "LGPLv2.1 & GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d8045f3b8f929c1cb29a1e3fd737b499 \
                    file://COPYING.tools;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "atk pangomm glibmm gtk+ cairomm"

inherit gnome autotools pkgconfig

INC_PR = "r0"

EXTRA_OECONF = " --disable-documentation "

LIBV = "2.4.0"

do_install_append () {
        install -d ${D}${includedir}/gtkmm-2.4
        install -m 0644 gdk/gdkmmconfig.h ${D}${includedir}/gtkmm-2.4
        install -m 0644 gtk/gtkmmconfig.h ${D}${includedir}/gtkmm-2.4
}

PACKAGES =+ "${PN}-demo"

FILES_${PN} = "${libdir}/lib*.so.*"
FILES_${PN}-dev += "${libdir}/*/include ${libdir}/*/proc/m4"
FILES_${PN}-demo += "${datadir}/*/demo/"
