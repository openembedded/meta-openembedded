SUMMARY = "Image viewer and browser"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

DEPENDS = " \
    glib-2.0 \
    glib-2.0-native \
    desktop-file-utils-native \
    exiv2 \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gtk4 \
    jpeg \
    lcms \
    libadwaita \
    libpng \
    libportal \
    zlib \
"

PACKAGECONFIG ?= "${@bb.utils.contains('DISTRO_FEATURES', 'polkit', 'colord', '', d)} libgif libjxl libraw librsvg libtiff libwebp"

PACKAGECONFIG[colord] = "-Dcolord=true,-Dcolord=false,colord"
PACKAGECONFIG[libgif] = "-Dlibgif=true,-Dlibgif=false,giflib"
PACKAGECONFIG[libheif] = "-Dlibheif=true,-Dlibheif=false,libheif"
PACKAGECONFIG[libjxl] = "-Dlibjxl=true,-Dlibjxl=false,libjxl"
PACKAGECONFIG[libraw] = "-Dlibraw=true,-Dlibraw=false,libraw"
PACKAGECONFIG[librsvg] = "-Dlibrsvg=true,-Dlibrsvg=false,librsvg"
PACKAGECONFIG[libtiff] = "-Dlibtiff=true,-Dlibtiff=false,tiff"
PACKAGECONFIG[libwebp] = "-Dlibwebp=true,-Dlibwebp=false,libwebp"

inherit gnomebase gsettings gtk-icon-cache mime-xdg vala features_check

REQUIRED_DISTRO_FEATURES = "opengl"

SRC_URI += "file://0001-util.h-Include-stdbool.h-for-bool.patch"
SRC_URI[archive.sha256sum] = "13cd9d6ce111262c25f4ff325bf36e9b806eec2d9b50be3c459cc2e69d4bbf69"

FILES:${PN} += "${datadir}/metainfo"
