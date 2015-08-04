SUMMARY = "Panel plugin to display current temperature and weather condition"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-weather-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin

DEPENDS += "libsoup-2.4 dbus-glib upower"

SRC_URI[md5sum] = "955581375d4860d10e52b5bc8935feff"
SRC_URI[sha256sum] = "927c3b2c699208d63b2baf469b74c8c0dfcb98006ca9f80d75e7aec62658e6fa"

FILES_${PN} += "${datadir}/xfce4/weather"
