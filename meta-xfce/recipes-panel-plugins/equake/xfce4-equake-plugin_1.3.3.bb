DESCRIPTION = "Equake XFCE is a panel plugin for the XFCE panel which monitors earthquakes and displays an update each time a new earthquake occurs."
HOMEPAGE = "http://freecode.com/projects/equake-xfce"
SECTION = "x11/application"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

inherit xfce-panel-plugin

SRC_URI = "http://www.e-quake.org/wp-uploads/2013/09/xfce4-equake-plugin-${PV}.tar.gz"
SRC_URI[md5sum] = "fa1e934f5b7e522efc4e30a99278f908"
SRC_URI[sha256sum] = "ea37e691606dbcd705f98cecccabd69b870c7acd008f47faae6d0fee21fe14a2"

FILES_${PN} += "${libdir}/xfce4/panel-plugins/xfce4-equake-plugin"
