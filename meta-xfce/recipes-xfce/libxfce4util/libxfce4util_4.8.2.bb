DESCRIPTION = "Basic utility library for Xfce4"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=252890d9eee26aab7b432e8b8a616475"
DEPENDS = "intltool-native xfce4-dev-tools-native glib-2.0"

PR = "r0"

inherit xfce

EXTRA_OECONF += "--with-broken-putenv=yes"

SRC_URI[md5sum] = "3376a77637a4292a863027d595548ee2"
SRC_URI[sha256sum] = "05a149841575cb961588742c8c9795c6f0d09f27d59546dd8a933b010d2ac816"
