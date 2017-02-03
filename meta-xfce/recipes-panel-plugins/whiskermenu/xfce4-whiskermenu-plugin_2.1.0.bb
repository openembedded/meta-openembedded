SUMMARY = "An alternate menu for the Xfce desktop environment"
HOMEPAGE = "http://gottcode.org/xfce4-whiskermenu-plugin/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin cmake

SRC_URI[md5sum] = "29240011d455d0fc591b4a1e2262b7a7"
SRC_URI[sha256sum] = "7e25706376e00fd216a9295aca3d0ff9f34bc13d4962bb6217e5f4270b973a80"

RRECOMMENDS_${PN} += "menulibre"
