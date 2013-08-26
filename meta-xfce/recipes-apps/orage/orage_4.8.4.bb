DESCRIPTION = "Xfce Calender"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"
DEPENDS = "gtk+ xfce4-panel libical popt"

inherit xfce-app

SRC_URI[md5sum] = "d5105981a9d77ed22be42615d70f3cc0"
SRC_URI[sha256sum] = "b19df727da9da48b979e238ac7113c803976de80bd1e2dadf6d84b864956dfd5"

PACKAGECONFIG ??= ""
PACKAGECONFIG[notify] = "--enable-libnotify,--disable-libnotify,libnotify"

PACKAGES =+ "xfce4-orageclock-plugin"
FILES_${PN} += "${datadir}/dbus-1"
FILES_${PN}-dbg += "${libdir}/orage/xfce4/panel-plugins/.debug"
FILES_xfce4-orageclock-plugin = "${libdir}/orage/xfce4/panel-plugins/xfce4-orageclock-plugin ${datadir}/xfce4/panel-plugins"

