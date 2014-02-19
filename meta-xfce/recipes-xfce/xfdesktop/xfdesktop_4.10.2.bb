SUMMARY = "Xfce4 Desktop Manager"
SECTION = "x11/base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "glib-2.0 gtk+ libxfce4util libxfce4ui libwnck xfconf dbus-glib thunar garcon exo"

inherit xfce

SRC_URI[md5sum] = "54a84ce63046c279fc3ec3f436d2f1b0"
SRC_URI[sha256sum] = "49a6e0be513e307e896f7e5929825babec9bbcd4b2e73552f9d27647a4db797d"

PACKAGECONFIG ??= ""
PACKAGECONFIG[notify] = "--enable-notifications,--disable-notifications,libnotify"

FILES_${PN} += "${datadir}/backgrounds"
