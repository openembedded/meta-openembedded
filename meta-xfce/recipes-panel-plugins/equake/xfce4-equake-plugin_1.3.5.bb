DESCRIPTION = "Equake XFCE is a panel plugin for the XFCE panel which monitors earthquakes and displays an update each time a new earthquake occurs."
HOMEPAGE = "http://freecode.com/projects/equake-xfce"
SECTION = "x11/application"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

inherit xfce-panel-plugin

SRC_URI[md5sum] = "7c520e2f1cd0cc5e33d8346b12e31c02"
SRC_URI[sha256sum] = "43bbc781575c357c13ac6720eb8e8485115099411d5382668e106de58ab0e416"

FILES_${PN} += "${libdir}/xfce4/panel-plugins/xfce4-equake-plugin"
