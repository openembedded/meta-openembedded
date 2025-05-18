SUMMARY = "Xfce configuration daemon and utilities"
SECTION = "x11/wm"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
DEPENDS = "libxfce4util perl intltool-native xfce4-dev-tools-native"

inherit xfce gtk-doc gobject-introspection bash-completion vala

EXTRA_OECONF += "PERL=${STAGING_DIR_TARGET}/usr/bin/perl"

SRC_URI[sha256sum] = "2e8c50160bf800a807aea094fc9dad81f9f361f42db56607508ed5b4855d2906"

FILES:${PN} += "${libdir}/xfce4/xfconf/xfconfd \
                ${libdir}/gio/modules/libxfconfgsettingsbackend.so \
                ${datadir}/dbus-1/services/org.xfce.Xfconf.service"

FILES:${PN}-dev += "${libdir}/gio/modules/libxfconfgsettingsbackend.la"

PACKAGECONFIG[gsettings-backend] = "--enable-gsettings-backend,--disable-gsettings-backend,"
