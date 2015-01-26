SUMMARY = "Panel plugin to display current temperature and weather condition"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-weather-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin

DEPENDS += "libsoup-2.4 dbus-glib upower"

SRC_URI[md5sum] = "cc6d9039540a71e57102ae75224a3f5e"
SRC_URI[sha256sum] = "be8ac0e5635355d568e4095a3459f53efd5f10a8ef46976a43dc30cbb277e5cd"

FILES_${PN} += "${datadir}/xfce4/weather"
