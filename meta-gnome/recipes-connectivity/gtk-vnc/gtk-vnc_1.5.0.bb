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

REQUIRED_DISTRO_FEATURES = "gobject-introspection-data"
GIR_MESON_OPTION = ""
VALA_MESON_OPTION = "with-vala"
VALA_MESON_ENABLE_FLAG ?= 'enabled'
VALA_MESON_DISABLE_FLAG ?= 'disabled'

PACKAGECONFIG[pulseaudio] = "-Dpulseaudio=enabled,-Dpulseaudio=disabled,pulseaudio"
PACKAGECONFIG[sasl] = "-Dsasl=enabled,-Dsasl=disabled,cyrus-sasl"

PACKAGECONFIG ??= "pulseaudio sasl"

inherit pkgconfig gnomebase gettext gobject-introspection vala features_check

PACKAGE_DEBUG_SPLIT_STYLE = "debug-without-src"

SRC_URI[archive.sha256sum] = "c0beb4747528ad931da43acc567c6a0190f7fc624465571ed9ccece02c34dd23"
