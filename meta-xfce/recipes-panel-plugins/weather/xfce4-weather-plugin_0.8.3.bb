SUMMARY = "Panel plugin to display current temperature and weather condition"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-weather-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin

DEPENDS += "libsoup-2.4 dbus-glib upower"

SRC_URI += " \
    file://0001-Make-plugin-ready-for-met.no-locationforecast-1.2-AP.patch \
    file://0002-Switch-to-met.no-locationforecastLTS-1.2-API-bug-109.patch \
"
SRC_URI[md5sum] = "755b33089c02afe88abb39253003a7f3"
SRC_URI[sha256sum] = "40a6a22be7653b15a47174a430da89040f178695b48e5e01e77990050f715ce4"

FILES_${PN} += "${datadir}/xfce4/weather"
