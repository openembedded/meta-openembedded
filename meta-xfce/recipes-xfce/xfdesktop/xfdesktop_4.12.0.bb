SUMMARY = "Xfce4 Desktop Manager"
SECTION = "x11/base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "glib-2.0 gtk+ libxfce4util libxfce4ui libwnck xfconf dbus-glib thunar garcon exo"

inherit xfce

SRC_URI[md5sum] = "d14545baf39678d3b1c7280f71e9d160"
SRC_URI[sha256sum] = "6e7a8b87317762214b6407576f6e2c1efcc176c136b4c7b869d18a74c87b7fc7"

PACKAGECONFIG ??= ""
PACKAGECONFIG[notify] = "--enable-notifications,--disable-notifications,libnotify"

FILES_${PN} += "${datadir}/backgrounds"
