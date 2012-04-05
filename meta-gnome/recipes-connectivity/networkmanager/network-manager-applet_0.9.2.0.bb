DESCRIPTION = "GTK+ applet for NetworkManager" 
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

DEPENDS = "gnome-bluetooth polkit-gnome libnotify networkmanager dbus-glib libglade gconf gnome-keyring libgnome-keyring iso-codes"

inherit gnome

SRC_URI[archive.md5sum] = "feaf2c8427d23924dde7de52ff4c5078"
SRC_URI[archive.sha256sum] = "287301692224cc1bb20abe8bc52140461f565e58898a99daef11a188bb29b362"

# GTK2.x mode
EXTRA_OECONF += "--with-gtkver=2 \
                 --with-bluetooth=yes \
                "

do_configure_append() {
        rm config.log
        # Sigh... --enable-compile-warnings=no doesn't actually turn off -Werror
        for i in $(find ${S} -name "Makefile") ; do
            sed -i -e s:-Werror::g $i
        done
}

RDEPENDS_${PN} =+ "networkmanager"
RRECOMMENDS_${PN} =+ "gnome-bluetooth gnome-keyring"

FILES_${PN} += "${datadir}/nm-applet/ \
        ${datadir}/libnm-gtk/wifi.ui \
        ${datadir}/gnome-vpn-properties/ \
        ${datadir}/gnome/autostart/ \
        "

FILES_${PN} += "${libdir}/gnome-bluetooth/plugins/*.so"
FILES_${PN}-dev += "${libdir}/gnome-bluetooth/plugins/libnma.la"
FILES_${PN}-staticdev += "${libdir}/gnome-bluetooth/plugins/libnma.a"
FILES_${PN}-dbg += "${libdir}/gnome-bluetooth/plugins/.debug/"

