DESCRIPTION = "GTK+ applet for NetworkManager" 
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

DEPENDS = "gnome-bluetooth polkit-gnome libnotify networkmanager dbus-glib libglade gconf gnome-keyring"

inherit gnome

SRC_URI[archive.md5sum] = "805d56f756e6844f16b57d9132f5ed81"
SRC_URI[archive.sha256sum] = "bb34653b7524bf36c79f86545a255d05c076367a2461389a7512b9e0ef0df38f"

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
RRECOMMENDS_${PN} =+ "gnome-bluetooth"

FILES_${PN} += "${datadir}/nm-applet/ \
        ${datadir}/gnome-vpn-properties/ \
        ${datadir}/gnome/autostart/ \
        "

FILES_${PN} += "${libdir}/gnome-bluetooth/plugins/*.so"
FILES_${PN}-dbg += "${libdir}/gnome-bluetooth/plugins/.debug/"

