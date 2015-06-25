SUMMARY = "Xfce4 Desktop Manager"
SECTION = "x11/base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "glib-2.0 gtk+ libxfce4util libxfce4ui libwnck xfconf dbus-glib thunar garcon exo"

inherit xfce

SRC_URI[md5sum] = "9a29c8e6b8a931fecd29f1ea736a6a70"
SRC_URI[sha256sum] = "c9788883163b57bac39d12e5f8310c869d176454879defb78b67f8e9f1ad5225"
SRC_URI += "file://0001-Remove-whitespaces-from-monitor-name-when-accessing-.patch"

PACKAGECONFIG ??= ""
PACKAGECONFIG[notify] = "--enable-notifications,--disable-notifications,libnotify"

FILES_${PN} += "${datadir}/backgrounds"
