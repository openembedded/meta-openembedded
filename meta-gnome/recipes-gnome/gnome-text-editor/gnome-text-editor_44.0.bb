SUMMARY = "A simple text editor"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=8f0e2cd40e05189ec81232da84bd6e1a"

GNOMEBASEBUILDCLASS = "meson"

DEPENDS = " \
    desktop-file-utils-native \
    libadwaita \
    gtk4 \
    gtksourceview5 \
    editorconfig-core-c \
    enchant2 \
"

GTKIC_VERSION = "4"

inherit gnomebase gtk-icon-cache itstool gnome-help mime-xdg features_check

REQUIRED_DISTRO_FEATURES = "opengl"

SRC_URI[archive.sha256sum] = "f67bc3780734ffa815fcc4c5daa7b555d24e459f81ea2b548e6a85c1612a31ca"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/dbus-1 \
"
