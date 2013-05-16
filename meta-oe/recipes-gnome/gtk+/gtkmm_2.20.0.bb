DESCRIPTION = "C++ bindings for the GTK+ toolkit."
HOMEPAGE = "http://www.gtkmm.org/"
SECTION = "libs"

LICENSE = "LGPLv2.1 & GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d8045f3b8f929c1cb29a1e3fd737b499 \
                    file://COPYING.tools;md5=751419260aa954499f7abaabaa882bbe"

PR = "r1"

DEPENDS = "atk pangomm glibmm gtk+ cairomm"

inherit gnome autotools pkgconfig

SRC_URI[archive.md5sum] = "c0cb0ea5bc1ab511d9b2461083f0445f"
SRC_URI[archive.sha256sum] = "c1199bbee423d6f0ef9b5e88973c5620a1e1c52f9f1298dffcfa36284a7fd91a"

EXTRA_OECONF = " --disable-documentation "

do_install_append () {
    install -d ${D}${includedir}/gtkmm-2.4
    install -m 0644 gdk/gdkmmconfig.h ${D}${includedir}/gtkmm-2.4
    install -m 0644 gtk/gtkmmconfig.h ${D}${includedir}/gtkmm-2.4
}

PACKAGES =+ "${PN}-demo"

FILES_${PN} = "${libdir}/lib*.so.*"
FILES_${PN}-dev += "${libdir}/*/include ${libdir}/*/proc/m4"
FILES_${PN}-demo += "${datadir}/*/demo/"

