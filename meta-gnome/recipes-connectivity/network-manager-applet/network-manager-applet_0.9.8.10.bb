SUMMARY = "GTK+ applet for NetworkManager"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

DEPENDS = "polkit-gnome libnotify libsecret networkmanager dbus-glib gconf gnome-keyring libgnome-keyring iso-codes"

inherit gnomebase gtk-icon-cache

GNOME_COMPRESS_TYPE = "xz"

SRC_URI += " \
    file://0001-remove-classes-and-properties-which-are-not-supporte.patch \
    file://0002-Add-support-for-AP-mode-setting-for-wifi-sharing.patch \
    file://0003-Use-AP-mode-for-network-sharing-if-device-supports-it.patch \
"
SRC_URI[archive.md5sum] = "5148348c139229c6a753f815f3f11e1c"
SRC_URI[archive.sha256sum] = "46fee9a1e667d1826e6a94bb6bd2e6bdbde535fc995e534542f5f7e8b8dae0cb"

# GTK2.x mode
EXTRA_OECONF += " \
    --with-gtkver=2 \
"

PACKAGECONFIG[bluetooth] = "--with-bluetooth,--without-bluetooth,gnome-bluetooth,gnome-bluetooth"
PACKAGECONFIG ??= ""

do_configure_append() {
    # Sigh... --enable-compile-warnings=no doesn't actually turn off -Werror
    for i in $(find ${B} -name "Makefile") ; do
        sed -i -e s:-Werror::g $i
    done
}

RDEPENDS_${PN} =+ "networkmanager"
RRECOMMENDS_${PN} =+ "gnome-keyring"

FILES_${PN} += " \
    ${datadir}/nm-applet/ \
    ${datadir}/libnm-gtk/wifi.ui \
"

FILES_${PN} += "${libdir}/gnome-bluetooth/plugins/*.so"
FILES_${PN}-dev += "${libdir}/gnome-bluetooth/plugins/libnma.la"
FILES_${PN}-staticdev += "${libdir}/gnome-bluetooth/plugins/libnma.a"
FILES_${PN}-dbg += "${libdir}/gnome-bluetooth/plugins/.debug/"
