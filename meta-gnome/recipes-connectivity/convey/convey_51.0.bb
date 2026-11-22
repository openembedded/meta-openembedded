SUMMARY = "Email application built around conversations for the GNOME desktop"
HOMEPAGE = "https://gitlab.gnome.org/donnybeelo/convey"
SECTION = "network"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=2a2244d5a13871ad950c55877546a6a2"

DEPENDS = " \
    appstream-native \
    cairo \
    desktop-file-utils-native \
    enchant2 \
    folks \
    gcr \
    gmime \
    gnome-online-accounts \
    gstreamer1.0 \
    gstreamer1.0-plugins-bad \
    gtk4 \
    icu \
    iso-codes \
    json-glib \
    libadwaita \
    libpeas \
    libsecret \
    libsoup \
    libstemmer \
    libxml2 \
    sqlite3 \
    webkitgtk \
"

inherit meson pkgconfig mime-xdg gsettings gtk-icon-cache gobject-introspection gnome-help vala features_check

SRC_URI = " \
    git://gitlab.gnome.org/donnybeelo/convey.git;protocol=https;branch=main;tag=${PV} \
    file://0001-meson-do-not-store-build-paths-in-the-binary.patch \
    file://0002-meson-do-not-check-for-the-iso-xml-files-and-the-C.u.patch \
    file://0003-vala-unit-look-up-g-ir-compiler-with-find_program.patch \
"
SRCREV = "d24c1da859fc9cc2cfb19872b1d8e972d4dc1a20"

REQUIRED_DISTRO_FEATURES = "gobject-introspection-data opengl"

GTKIC_VERSION = "4"
GIR_MESON_OPTION = ""

EXTRA_OEMESON = " \
    -Dprofile=release \
    -Drevno=${PV} \
    -Diso_639_xml=${datadir}/xml/iso-codes/iso_639.xml \
    -Diso_3166_xml=${datadir}/xml/iso-codes/iso_3166.xml \
    -Dvaladoc=disabled \
"

PACKAGECONFIG ??= ""
PACKAGECONFIG[libunwind] = "-Dlibunwind=enabled,-Dlibunwind=disabled,libunwind"
PACKAGECONFIG[tnef] = "-Dtnef=enabled,-Dtnef=disabled,libytnef"

FILES:${PN} += "${datadir}"
