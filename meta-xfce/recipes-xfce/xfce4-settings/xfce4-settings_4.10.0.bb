DESCRIPTION = "Xfce4 settings"
SECTION = "x11/wm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo gtk+ libxfce4util libxfce4ui xfconf dbus-glib libxi virtual/libx11 xrandr libnotify libxcursor libxklavier"

inherit xfce

SRC_URI += "file://0001-xsettings.xml-remove-trouble-causing-comment.patch \
            file://0002-xsettings.xml-Set-default-themes.patch"
SRC_URI[md5sum] = "cc4dd9179ead9046c056431f01a12000"
SRC_URI[sha256sum] = "0843f09ba9673f1d1b5df8dce4a17b63c183a9ba3be75fb6ef99a67fc8c1f77e"

FILES_${PN} += "${libdir}/xfce4"

RRECOMMENDS_${PN} += "gnome-icon-theme"
