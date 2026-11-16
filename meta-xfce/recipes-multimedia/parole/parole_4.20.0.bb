DESCRIPTION = "Parole is a modern simple media player based on the GStreamer framework"
HOMEPAGE = "https://docs.xfce.org/apps/parole/start"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

XFCE_COMPRESS_TYPE = "xz"
XFCEBASEBUILDCLASS = "meson"
GTKDOC_MESON_OPTION = "gtk-doc"

inherit xfce-app gtk-doc mime mime-xdg

DEPENDS += " \
    dbus-glib \
    dbus-glib-native \
    xfce4-dev-tools-native \
    libxfce4util \
    libxfce4ui \
    xfconf \
    gstreamer1.0-plugins-base \
    taglib \
"

SRC_URI[sha256sum] = "5cf753e670d6518701133eb860d8bceb3a08a496af6a2b7cc67b93320230c983"

RDEPENDS:${PN} += "gstreamer1.0-plugins-good"

EXTRA_OEMESON = "-Dtaglib=enabled"

PACKAGECONFIG ??= "notify ${@bb.utils.filter('DISTRO_FEATURES', 'wayland x11', d)}"
PACKAGECONFIG[notify] = "-Dnotify-plugin=enabled,-Dnotify-plugin=disabled,libnotify"
PACKAGECONFIG[wayland] = "-Dwayland=enabled,-Dwayland=disabled"
PACKAGECONFIG[x11] = "-Dx11=enabled,-Dx11=disabled,virtual/libx11"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${libdir}/parole-0/*.so \
"
