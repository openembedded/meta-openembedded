SUMMARY = "GNOME Console"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=8f0e2cd40e05189ec81232da84bd6e1a"

GTKIC_VERSION = "4"
inherit gnomebase gsettings pkgconfig gtk-icon-cache
REQUIRED_DISTRO_FEATURES = "opengl"

DEPENDS = " \
    desktop-file-utils-native \
    gtk4-native \
    glib-2.0 \
    gsettings-desktop-schemas \
    hicolor-icon-theme \
    libadwaita \
    libgtop \
    pcre2 \
    vte \
"
SRC_URI += "file://0001-include-locale.h-for-setlocale.patch"
SRC_URI[archive.sha256sum] = "487ec0de0a24f12ef6f778e4aee98d744a9dcc921c9e7df98b2d9f410b00ef52"

PACKAGECONFIG ?= ""
PACKAGECONFIG[tests] = "-Dtests=true,-Dtests=false"
PACKAGECONFIG[devel] = "-Ddevel=true,-Ddevel=false"

FILES:${PN} += "${datadir}"
