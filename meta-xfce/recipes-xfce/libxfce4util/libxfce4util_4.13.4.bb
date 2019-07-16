SUMMARY = "Basic utility library for Xfce4"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=252890d9eee26aab7b432e8b8a616475"
DEPENDS = "intltool-native xfce4-dev-tools-native glib-2.0"

inherit xfce gtk-doc gobject-introspection

SRC_URI[md5sum] = "ebb1d229a7a7f86a969a72b8d97c8531"
SRC_URI[sha256sum] = "57dd4ba226432afdf98cea4700a4679bb1b57ce80713167f99fde50ad87d0863"
