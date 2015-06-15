SUMMARY = "GTK+ applet for NetworkManager"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

DEPENDS = "gtk+3 libnotify libsecret networkmanager dbus-glib gconf gnome-keyring libgnome-keyring iso-codes"

inherit gnomebase gsettings gtk-icon-cache

GNOME_COMPRESS_TYPE = "xz"

SRC_URI += " \
    file://0001-Add-support-for-AP-mode-setting-for-wifi-sharing.patch \
    file://0002-Use-AP-mode-for-network-sharing-if-device-supports-it.patch \
"
SRC_URI[archive.md5sum] = "5b2a8baa6b038b3e55e5444ff321e80d"
SRC_URI[archive.sha256sum] = "760af85b43fa5bcf74b96f899a6d4c1d5e9445e5eb63088c49e5164bbf88d9a9"

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
RRECOMMENDS_${PN} =+ "gnome-keyring"

FILES_${PN} += " \
    ${datadir}/nm-applet/ \
    ${datadir}/libnm-gtk/wifi.ui \
"

FILES_${PN} += "${libdir}/gnome-bluetooth/plugins/*.so"
FILES_${PN}-dev += "${libdir}/gnome-bluetooth/plugins/libnma.la"
FILES_${PN}-staticdev += "${libdir}/gnome-bluetooth/plugins/libnma.a"
FILES_${PN}-dbg += "${libdir}/gnome-bluetooth/plugins/.debug/"
