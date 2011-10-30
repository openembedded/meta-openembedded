DESCRIPTION = "Panel plugin with graphical representation of the cpu frequency"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-eyes-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit xfce-panel-plugin

# SRC_URI must follow inherited one
SRC_URI += "file://port-to-libxfce4ui.patch"
SRC_URI[md5sum] = "dcbf6ea9035d379d168b479be0d09f14"
SRC_URI[sha256sum] = "eaae5d230b5c2fe6a8b448b59cfe959bf126796911c166a7343a51dc440fbbb2"

FILES_${PN} += "${datadir}/xfce4/eyes"
