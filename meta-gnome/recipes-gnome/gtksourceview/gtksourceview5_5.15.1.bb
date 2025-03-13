SUMMARY = "Portable C library for multiline text editing"
HOMEPAGE = "http://projects.gnome.org/gtksourceview/"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

DEPENDS = " \
    fribidi \
    glib-2.0-native \
    fontconfig \
    gtk4 \
    libxml2 \
    libpcre2 \
    pango \
"

PNAME = "gtksourceview"

S = "${WORKDIR}/${PNAME}-${PV}"


inherit gnomebase lib_package gettext features_check gi-docgen gtk-icon-cache gobject-introspection vala

REQUIRED_DISTRO_FEATURES = "opengl"

SRC_URI = "https://download.gnome.org/sources/gtksourceview/5.15/${PNAME}-${PV}.tar.xz"
SRC_URI[sha256sum] = "39c0c82148c088a7a65765c4fb8f9f045590bf0b464930ba32c9f709b2a5f40e"

GIR_MESON_ENABLE_FLAG = 'enabled'
GIR_MESON_DISABLE_FLAG = 'disabled'
GIDOCGEN_MESON_OPTION = "documentation"

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'vulkan', d)}"
PACKAGECONFIG[vulkan] = ",,vulkan-loader vulkan-headers"

FILES:${PN} += "${datadir}/gtksourceview-5"
