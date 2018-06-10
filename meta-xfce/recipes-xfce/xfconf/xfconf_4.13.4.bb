SUMMARY = "Xfce configuration daemon and utilities"
SECTION = "x11/wm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
DEPENDS = "dbus-glib libxfce4util perl intltool-native xfce4-dev-tools-native"

inherit xfce gtk-doc

EXTRA_OECONF += "PERL=${STAGING_DIR_TARGET}/usr/bin/perl"

SRC_URI[md5sum] = "200d46c4e5a6019bb2b1cc3948b90c9c"
SRC_URI[sha256sum] = "4808997894ad9b6bcd39b0e8c609129e60d204c038d4edc4e9b8c55ea2d094c9"

FILES_${PN} += "${libdir}/xfce4/xfconf/xfconfd \
                ${datadir}/dbus-1/services/org.xfce.Xfconf.service"
