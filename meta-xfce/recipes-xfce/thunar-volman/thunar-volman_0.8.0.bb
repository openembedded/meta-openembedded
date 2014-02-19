SUMMARY = "Automatic management of removable drives and media for thunar"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo glib-2.0 gtk+ libxfce4ui libxfce4util xfconf libnotify udev"

inherit xfce

SRC_URI[md5sum] = "250af757ea629c7c27f554d17119080c"
SRC_URI[sha256sum] = "ff0887c862b578580d05f4cd7db66081382ff143f9cc7ea3c9ba58cf5d02bceb"

PACKAGECONFIG ??= ""
PACKAGECONFIG[notify] = "--enable-notifications,--disable-notifications,libnotify"

RDEPENDS_${PN} = "eject"
