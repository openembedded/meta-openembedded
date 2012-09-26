DESCRIPTION = "Xfce4 Desktop Manager"
SECTION = "x11/base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "glib-2.0 gtk+ libxfce4util libxfce4ui libwnck xfconf dbus-glib thunar garcon exo"

inherit xfce

SRC_URI[md5sum] = "d5f6fb9fdde3ddff5804b2a251892936"
SRC_URI[sha256sum] = "897ae6ee435dcc89809ad70c15c5d15347d1cf4fc8033238b17dcc47836c2d7b"

FILES_${PN} += "${datadir}/backgrounds"
