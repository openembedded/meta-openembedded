DESCRIPTION = "Xfce4 Widget library and X Window System interaction"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=252890d9eee26aab7b432e8b8a616475"
DEPENDS = "gtk+ gettext intltool libxfce4util startup-notification xfconf"

PR = "r0"

inherit xfce

FILES_${PN} += "${libdir}/xfce4/modules ${libdir}/libglade/2.0/*.so ${datadir}/xfce4/mime ${datadir}/icons/hicolor"

SRC_URI[md5sum] = "df9acb3328dff905bd0777b84532b69f"
SRC_URI[sha256sum] = "21f67fd004653440bfdb44874ed69c9806b4784978ec068d0b1c7012864ba339"
