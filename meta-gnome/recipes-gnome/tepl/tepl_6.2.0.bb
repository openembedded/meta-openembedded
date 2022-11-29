SUMMARY = "Tepl library eases the development of GtkSourceView-based projects"
LICENSE = "LGPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-3.0-or-later.txt;md5=c51d3eef3be114124d11349ca0d7e117"

DEPENDS = " \
    gsettings-desktop-schemas \
    glib-2.0 \
    gtk+3 \
    gtksourceview4 \
    amtk \
    libxml2 \
    uchardet \
    gtk-doc-native \
    libxslt-native \
    docbook-xsl-stylesheets-native \
"

inherit meson gobject-introspection gettext features_check pkgconfig


ANY_OF_DISTRO_FEATURES = "${GTK3DISTROFEATURES}"
SRC_URI = "git://gitlab.gnome.org/swilmet/tepl;protocol=https;branch=main"
SRCREV = "34973a0d48ba5a0dd0a776c66bfc0c3f65682d9c"
S = "${WORKDIR}/git"

# gobject-introspection is mandatory and cannot be configured
REQUIRED_DISTRO_FEATURES += "gobject-introspection-data"
GIR_MESON_OPTION = ""

GTKDOC_MESON_OPTION = "gtk_doc"
