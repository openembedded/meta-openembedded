DESCRIPTION = "Parole is a modern simple media player based on the GStreamer framework"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/parole"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

inherit xfce-app gtk-doc mime

DEPENDS = " \
    glib-2.0 \
    dbus-glib \
    gtk+3 \
    \
    xfce4-dev-tools-native \
    libxfce4util \
    libxfce4ui \
    xfconf \
    \
    gstreamer1.0-plugins-base \
    taglib \
"

SRC_URI[md5sum] = "fffdc23d2aa22271f01410a9e27c3404"
SRC_URI[sha256sum] = "2d966aeb426de81d992829e33b3f66185b19fd031a1891968b3a40d6d50239cc"
SRC_URI += "file://parole-0.8.0-appdata.patch"

RDEPENDS_${PN} += "gstreamer1.0-plugins-good"

PACKAGECONFIG ??= "notify"
PACKAGECONFIG[clutter] = "--enable-clutter, --disable-clutter, clutter"
PACKAGECONFIG[notify] = "--enable-notify-plugin, --disable-notify-plugin, libnotify"

FILES_${PN} += " \
    ${datadir}/appdata \
    ${libdir}/parole-0/*.so \
"
FILES_${PN}-dbg += "${libdir}/parole-0/.debug"
FILES_${PN}-dev += "${libdir}/parole-0/*.la"
