SUMMARY = "Basic utility library for Xfce4"
HOMEPAGE = "https://docs.xfce.org/xfce/libxfce4util/start"
SECTION = "x11/libs"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=4cf66a4984120007c9881cc871cf49db"
DEPENDS = "xfce4-dev-tools-native glib-2.0"

XFCE_COMPRESS_TYPE = "xz"
XFCEBASEBUILDCLASS = "meson"
GTKDOC_MESON_OPTION = "gtk-doc"

inherit xfce gtk-doc gobject-introspection vala

SRC_URI[sha256sum] = "205677cb96b778e5079eeac80f3a636d2a909b08fcf2aa05bfe4513c69c27f29"
