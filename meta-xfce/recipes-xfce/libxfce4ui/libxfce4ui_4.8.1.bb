DESCRIPTION = "Xfce4 Widget library and X Window System interaction"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=252890d9eee26aab7b432e8b8a616475"
DEPENDS = "gtk+ intltool libxfce4util startup-notification xfconf"

inherit xfce

FILES_${PN} += "${libdir}/xfce4/modules ${libdir}/libglade/2.0/*.so ${datadir}/xfce4/mime ${datadir}/icons/hicolor"

SRC_URI[md5sum] = "30bd432dd0f19305e846a122d4c7c6fe"
SRC_URI[sha256sum] = "39cbed29da06960074014b74abc2ba57db49dc81ab8995fb2160579c51669956"
