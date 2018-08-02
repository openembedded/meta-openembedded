SUMMARY = "GTK+ applet for NetworkManager"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

DEPENDS = "gtk+3 libnotify libsecret networkmanager dbus-glib \
           gconf libgnome-keyring iso-codes nss \
           intltool-native \
"

inherit distro_features_check gnomebase gsettings gtk-icon-cache gobject-introspection

REQUIRED_DISTRO_FEATURES = "x11"

SRC_URI[archive.md5sum] = "bcdd92645d36fc5778f27aeb2773509b"
SRC_URI[archive.sha256sum] = "ca9b1233721f700a7190b98eea69ce6d0ccf74fbfde173ac868a4ec7a78840cb"

PACKAGECONFIG[modemmanager] = "--with-wwan,--without-wwan,modemmanager"
PACKAGECONFIG ??= ""

EXTRA_OECONF = " \
    --without-selinux \
"

do_configure_append() {
    # Sigh... --enable-compile-warnings=no doesn't actually turn off -Werror
    for i in $(find ${B} -name "Makefile") ; do
        sed -i -e s%-Werror[^[:space:]]*%%g $i
    done
}

# gobject-introspection related
GI_DATA_ENABLED_libc-musl = "False"

do_compile_prepend() {
    export GIR_EXTRA_LIBS_PATH="${B}/src/libnma/.libs:${B}/src/libnm-gtk/.libs"
}

RDEPENDS_${PN} =+ "networkmanager"

FILES_${PN} += " \
    ${datadir}/nm-applet/ \
    ${datadir}/libnm-gtk/wifi.ui \
    ${datadir}/libnma/wifi.ui \
    ${datadir}/metainfo \
"

# musl builds generate gir files which otherwise go un-packaged
FILES_${PN}-dev += " \
    ${datadir}/gir-1.0 \
"
