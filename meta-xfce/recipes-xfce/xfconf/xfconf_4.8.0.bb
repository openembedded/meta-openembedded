DESCRIPTION = "Xfce configuration daemon and utilities"
SECTION = "x11/wm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
DEPENDS = "dbus-glib libxfce4util perl-native"

PR = "r0"

inherit xfce

FILES_${PN} += "${libdir}/xfce4/xfconf/xfconfd \
                ${datadir}/dbus-1/services/org.xfce.Xfconf.service"

SRC_URI[md5sum] = "0f11ed1ec7789c5c4c3fcc7cdb3c2940"
SRC_URI[sha256sum] = "e071d55982238f997a691ff4bb08ef39e4c86bae31cf69b6666bc0cdadd1b85d"
