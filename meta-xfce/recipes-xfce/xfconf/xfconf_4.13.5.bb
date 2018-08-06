SUMMARY = "Xfce configuration daemon and utilities"
SECTION = "x11/wm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
DEPENDS = "dbus-glib libxfce4util perl intltool-native xfce4-dev-tools-native"

inherit xfce gtk-doc

EXTRA_OECONF += "PERL=${STAGING_DIR_TARGET}/usr/bin/perl"

SRC_URI[md5sum] = "1ce1a7f694fb6a4a4a32000f72131ea9"
SRC_URI[sha256sum] = "90b4eb0cdf407339c091e9811f7a40b4cfa215ebb03a921503b7e21f0f4f63cb"

FILES_${PN} += "${libdir}/xfce4/xfconf/xfconfd \
                ${datadir}/dbus-1/services/org.xfce.Xfconf.service"
