SUMMARY = "A light and fast file manager"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

DEPENDS += " \
    desktop-file-utils-native \
    glib-2.0-native \
    gtk+ \
"

CXXFLAGS += "-D_LIBCPP_ENABLE_CXX17_REMOVED_AUTO_PTR"

inherit gnomebase itstool gettext gnome-help features_check meson gtk-icon-cache mime-xdg
ANY_OF_DISTRO_FEATURES = "${GTK2DISTROFEATURES}"

GNOMEBASEBUILDCLASS = "meson"
GIR_MESON_ENABLE_FLAG = 'enabled'
GIR_MESON_DISABLE_FLAG = 'disabled'

SRC_URI[archive.sha256sum] = "d80ac70b99a7059c8f28ca0bf37305f09daeaecfceb1528d68a435ace9e8ec5d"

PACKAGECONFIG ??= "exiv2 taglib libgsf poppler"
PACKAGECONFIG[exiv2] = "-Dexiv2=enabled,-Dexiv2=disabled,exiv2"
PACKAGECONFIG[taglib] = "-Dtaglib=enabled,-Dtaglib=disabled,taglib"
PACKAGECONFIG[libgsf] = "-Dlibgsf=enabled,-Dlibgsf=disabled,libgsf"
PACKAGECONFIG[poppler] = "-Dpoppler=enabled,-Dpoppler=disabled,poppler"
PACKAGECONFIG[samba] = "-Dsamba=enabled,-Dsamba=disabled,samba"
PACKAGECONFIG[tests] = "-Dtests=enabled,-Dtests=disabled,"

FILES:${PN} += "${datadir}/metainfo"
FILES:${PN}-dev += "${libdir}/${BPN}/lib*${SOLIBSDEV}"
