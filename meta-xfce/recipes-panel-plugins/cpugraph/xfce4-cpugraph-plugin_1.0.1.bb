DESCRIPTION = "Panel plugin with graphical representation of the cpu frequency"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-cpugraph-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=415654f59d8fa70fe4eac2c3f86c8f5e"

inherit xfce-panel-plugin

# SRC_URI must follow inherited one
SRC_URI += "file://port-to-libxfce4ui.patch"
SRC_URI[md5sum] = "ff551087b1f61c47b8746e8687c572aa"
SRC_URI[sha256sum] = "b5fada35a92083b67818da0ce3fe5ba6a2443fee26d6eec71755ff383df7c407"
