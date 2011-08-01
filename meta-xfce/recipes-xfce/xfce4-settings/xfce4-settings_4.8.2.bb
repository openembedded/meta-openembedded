DESCRIPTION = "Xfce4 settings"
SECTION = "x11/wm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo gtk+ libxfce4util libxfce4ui xfconf dbus-glib libxi virtual/libx11 xrandr libnotify libxcursor libxklavier"

PR = "r0"

inherit xfce

SRC_URI[md5sum] = "a45420fea20f29265ab6785cb1d07377"
SRC_URI[sha256sum] = "3f79865a6a3817f3c868f2412cbf105e73e54810438f62035699ae5fd72c29d0"
