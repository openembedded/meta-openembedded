SUMMARY = "A VNC client viewer widget for GTK"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=4339efb5fd592e45b9e2641de9fe734f"

DEPENDS = " \
    gdk-pixbuf \
    glib-2.0 \
    gnutls \
    gtk+3 \
    libgcrypt \
    libx11 \
    zlib \
"

GNOMEBASEBUILDCLASS = "meson"
REQUIRED_DISTRO_FEATURES = "gobject-introspection-data"
GIR_MESON_OPTION = ""

PACKAGECONFIG[pulseaudio] = "-Dpulseaudio=enabled,-Dpulseaudio=disabled,pulseaudio"
PACKAGECONFIG[sasl] = "-Dsasl=enabled,-Dsasl=disabled,cyrus-sasl"

PACKAGECONFIG ??= "pulseaudio sasl"

inherit pkgconfig gnomebase gettext gobject-introspection vala features_check

SRC_URI[archive.sha256sum] = "512763ac4e0559d0158b6682ca5dd1a3bd633f082f5e4349d7158e6b5f80f1ce"
