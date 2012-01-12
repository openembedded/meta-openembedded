DESCRIPTION = "Gnome background images"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SECTION = "x11/gnome"

inherit gnome

SRC_URI[archive.md5sum] = "3df26626483b02e51adefc6ab5945a8d"
SRC_URI[archive.sha256sum] = "4d7b60b5ba768bf8834b5fa3a3471cd9a9e14b5884bc210dc2d3cdbf1faddcef"

FILES_${PN} += "${datadir}/gnome-background-properties"

