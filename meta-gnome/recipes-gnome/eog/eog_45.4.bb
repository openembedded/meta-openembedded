SUMMARY = "This is the Eye of GNOME, an image viewer program."
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = " \
    librsvg \
    gnome-desktop \
    gsettings-desktop-schemas \
    gdk-pixbuf \
    gtk+3 \
    libhandy \
    libpeas \
    libportal \
    libexif \
    lcms \
"


inherit gnomebase pkgconfig gsettings gobject-introspection gettext mime-xdg features_check gtk-icon-cache

# FIXME: whilst eog uses libpeas <2, g-i is needed. This can be removed when libpeas2 is used.
REQUIRED_DISTRO_FEATURES = "opengl gobject-introspection-data"

SRC_URI[archive.sha256sum] = "b50f321c70ac64af7bcaa5b4460dc675b3ca60fdad385616f3ac7b770e0666fe"

GTKDOC_MESON_OPTION = "gtk_doc"

EXTRA_OEMESON = "-Dxmp=false"

FILES:${PN} += "${datadir}"
