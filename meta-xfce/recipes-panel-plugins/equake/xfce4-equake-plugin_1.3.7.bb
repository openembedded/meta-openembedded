DESCRIPTION = "Equake XFCE is a panel plugin for the XFCE panel which monitors earthquakes and displays an update each time a new earthquake occurs."
HOMEPAGE = "http://freecode.com/projects/equake-xfce"
SECTION = "x11/application"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

inherit xfce-panel-plugin

SRC_URI[md5sum] = "4f8b8fc85e9506f43c9343f3c3c99ae1"
SRC_URI[sha256sum] = "6b9e14c2f54060d182b61e90b4c84a79b57aab1955e3ff877c757f26cefa23bf"

FILES_${PN} += "${libdir}/xfce4/panel-plugins/xfce4-equake-plugin"
