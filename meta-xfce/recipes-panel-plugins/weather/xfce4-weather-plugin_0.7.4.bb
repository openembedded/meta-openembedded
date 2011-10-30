DESCRIPTION = "Panel plugin to display current temperature and weather condition"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-weather-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit xfce-panel-plugin

SRC_URI += "file://port-to-libxfce4ui.patch"
SRC_URI[md5sum] = "03c972d13eba5cd226432ff66e3ff282"
SRC_URI[sha256sum] = "3f76207b8a845d15bfec6825bd5300aedd086c455259c4dd8670a89a3c8ab382"

FILES_${PN} += "${datadir}/xfce4/weather"
