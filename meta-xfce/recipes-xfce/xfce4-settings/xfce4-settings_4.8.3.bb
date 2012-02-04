DESCRIPTION = "Xfce4 settings"
SECTION = "x11/wm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo gtk+ libxfce4util libxfce4ui xfconf dbus-glib libxi virtual/libx11 xrandr libnotify libxcursor libxklavier"

inherit xfce

SRC_URI += "file://0001-xfsettingsd-use-gnome-as-default-icon-theme.patch"

SRC_URI[md5sum] = "4669298cc8a4abf0e5a410016b575030"
SRC_URI[sha256sum] = "d878cc474e1e677e9607279b9a1007cb90ed35ff078de180259a8b2b8d06bc2e"

RRECOMMENDS_${PN} += "gnome-icon-theme"
