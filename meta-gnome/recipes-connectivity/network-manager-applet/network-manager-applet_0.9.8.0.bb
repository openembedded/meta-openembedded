DESCRIPTION = "GTK+ applet for NetworkManager" 
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

DEPENDS = "gnome-bluetooth polkit-gnome libnotify networkmanager dbus-glib libglade gconf gnome-keyring libgnome-keyring iso-codes"

inherit gnome gtk-icon-cache

GNOME_COMPRESS_TYPE = "xz"

SRC_URI += " \
    file://0001-remove-classes-and-properties-which-are-not-supporte.patch \
    file://images/* \
"
SRC_URI[archive.md5sum] = "531ce56c51ec86c5d2dc4cbe58649583"
SRC_URI[archive.sha256sum] = "1afb6e736870ba95132bf0d211c46849e02a820ba3902a059a616be888053590"

# GTK2.x mode
EXTRA_OECONF += " \
    --with-gtkver=2 \
    --with-bluetooth=yes \
"

do_configure_append() {
    rm config.log
    # Sigh... --enable-compile-warnings=no doesn't actually turn off -Werror
    for i in $(find ${S} -name "Makefile") ; do
        sed -i -e s:-Werror::g $i
    done
}

do_install_append() {
    install -m 0644 ${WORKDIR}/icons/* ${D}/usr/share/icons/hicolor/22x22/apps/
}

RDEPENDS_${PN} =+ "networkmanager"
RRECOMMENDS_${PN} =+ "gnome-bluetooth gnome-keyring"

FILES_${PN} += " \
    ${datadir}/nm-applet/ \
    ${datadir}/libnm-gtk/wifi.ui \
"

FILES_${PN} += "${libdir}/gnome-bluetooth/plugins/*.so"
FILES_${PN}-dev += "${libdir}/gnome-bluetooth/plugins/libnma.la"
FILES_${PN}-staticdev += "${libdir}/gnome-bluetooth/plugins/libnma.a"
FILES_${PN}-dbg += "${libdir}/gnome-bluetooth/plugins/.debug/"

