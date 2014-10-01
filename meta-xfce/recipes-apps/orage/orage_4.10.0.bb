SUMMARY = "Xfce Calender"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"
DEPENDS = "gtk+ xfce4-panel libical popt"

inherit xfce-app

SRC_URI[md5sum] = "b33fa272d92f539a224a7a40b1926dfc"
SRC_URI[sha256sum] = "6cb854f3437e31bab23abfce97bd10afa1636a9a1480d80e14e26c85d06be7c0"

PACKAGECONFIG ??= ""
PACKAGECONFIG[notify] = "--enable-libnotify,--disable-libnotify,libnotify"

PACKAGES =+ "xfce4-orageclock-plugin"
FILES_${PN} += "${datadir}/dbus-1"
FILES_${PN}-dbg += "${libdir}/orage/xfce4/panel-plugins/.debug"
FILES_xfce4-orageclock-plugin = "${libdir}/orage/xfce4/panel-plugins/xfce4-orageclock-plugin ${datadir}/xfce4/panel-plugins"

