DESCRIPTION = "Equake XFCE is a panel plugin for the XFCE panel which monitors earthquakes and displays an update each time a new earthquake occurs."
HOMEPAGE = "http://freecode.com/projects/equake-xfce"
SECTION = "x11/application"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

inherit xfce-panel-plugin

SRC_URI[md5sum] = "ee76ed3825999b24ee9c2c976d906e22"
SRC_URI[sha256sum] = "8129067080c8a4656063e7cde20f13fabb47b49c84ce610916f54644acb93d67"

FILES_${PN} += "${libdir}/xfce4/panel-plugins/xfce4-equake-plugin"
