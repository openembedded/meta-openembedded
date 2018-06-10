SUMMARY = "An alternate menu for the Xfce desktop environment"
HOMEPAGE = "http://gottcode.org/xfce4-whiskermenu-plugin/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin cmake

SRC_URI[md5sum] = "2eda14e43e37222035be619327c604cb"
SRC_URI[sha256sum] = "8f68492a38cb2d148867162adff947dd37fed162b4ef4f31910660229ef00ef1"

RRECOMMENDS_${PN} += "menulibre"
