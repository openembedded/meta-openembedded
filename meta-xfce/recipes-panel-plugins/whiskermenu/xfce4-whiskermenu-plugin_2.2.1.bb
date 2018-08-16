SUMMARY = "An alternate menu for the Xfce desktop environment"
HOMEPAGE = "http://gottcode.org/xfce4-whiskermenu-plugin/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin cmake

SRC_URI[md5sum] = "8d202b984e6bdf9c96fc8b0701f881cc"
SRC_URI[sha256sum] = "b5f8efcc94cb26e4394ae599ac5955d745f3c11c39605036709ede5454f302b9"

RRECOMMENDS_${PN} += "menulibre"
