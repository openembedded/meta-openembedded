SUMMARY = "GTK+ applet for NetworkManager"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

DEPENDS = "gtk+3 libnotify libsecret networkmanager dbus-glib gconf libgnome-keyring iso-codes"

inherit gnomebase gsettings gtk-icon-cache

GNOME_COMPRESS_TYPE = "xz"

SRC_URI += " \
    file://0001-Add-support-for-AP-mode-setting-for-wifi-sharing.patch \
    file://0002-Use-AP-mode-for-network-sharing-if-device-supports-it.patch \
"
SRC_URI[archive.md5sum] = "7fc2ed3f0c46ed41ddabe99d51513b1c"
SRC_URI[archive.sha256sum] = "2cc26f03d0011d2a067bd5205dc0c17d5aa0470020a8c36d319ae10e5728af72"

PACKAGECONFIG[bluetooth] = "--with-bluetooth,--without-bluetooth,gnome-bluetooth,gnome-bluetooth"
PACKAGECONFIG[modemmanager] = "--with-modem-manager-1,--without-modem-manager-1,modemmanager"
PACKAGECONFIG ??= ""

do_configure_append() {
    # Sigh... --enable-compile-warnings=no doesn't actually turn off -Werror
    for i in $(find ${B} -name "Makefile") ; do
        sed -i -e s:-Werror::g $i
    done
}

RDEPENDS_${PN} =+ "networkmanager"

FILES_${PN} += " \
    ${datadir}/nm-applet/ \
    ${datadir}/libnm-gtk/wifi.ui \
"

FILES_${PN} += "${libdir}/gnome-bluetooth/plugins/*.so"
FILES_${PN}-dev += "${libdir}/gnome-bluetooth/plugins/libnma.la"
FILES_${PN}-staticdev += "${libdir}/gnome-bluetooth/plugins/libnma.a"
FILES_${PN}-dbg += "${libdir}/gnome-bluetooth/plugins/.debug/"
