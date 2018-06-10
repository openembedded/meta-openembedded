SUMMARY = "Basic utility library for Xfce4"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=252890d9eee26aab7b432e8b8a616475"
DEPENDS = "intltool-native xfce4-dev-tools-native glib-2.0"

inherit xfce gtk-doc gobject-introspection

SRC_URI[md5sum] = "d1d81519accfd09b5bbdc6ec0f531366"
SRC_URI[sha256sum] = "278013dca14bfd50c9efd3898a28e3f3c5053b506de257267947fbb852d2ed2c"
