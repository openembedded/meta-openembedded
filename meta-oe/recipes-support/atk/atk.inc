DESCRIPTION = "An accessibility toolkit for GNOME."
SECTION = "x11/libs"
LICENSE = "LGPL"

DEPENDS = "glib-2.0 gtk-doc-native"

inherit autotools pkgconfig

EXTRA_OECONF = "--disable-glibtest"

