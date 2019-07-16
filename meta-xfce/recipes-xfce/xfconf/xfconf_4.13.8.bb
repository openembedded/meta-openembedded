SUMMARY = "Xfce configuration daemon and utilities"
SECTION = "x11/wm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
DEPENDS = "libxfce4util perl intltool-native xfce4-dev-tools-native"

inherit xfce gtk-doc gobject-introspection

EXTRA_OECONF += "PERL=${STAGING_DIR_TARGET}/usr/bin/perl"

SRC_URI[md5sum] = "5d48e8d50f7bfadd31e804ce602a5cf3"
SRC_URI[sha256sum] = "55e2441c4ff78a7006fabb59647f7bc1f190cbb82327b93ba063ff624a896591"

FILES_${PN} += "${libdir}/xfce4/xfconf/xfconfd \
                ${libdir}/gio/modules/libxfconfgsettingsbackend.so \
                ${datadir}/dbus-1/services/org.xfce.Xfconf.service"

FILES_${PN}-dev += "${libdir}/gio/modules/libxfconfgsettingsbackend.la"

PACKAGECONFIG[gsettings-backend] = "--enable-gsettings-backend,--disable-gsettings-backend,"
