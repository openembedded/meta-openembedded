SUMMARY = "An alternate menu for the Xfce desktop environment"
HOMEPAGE = "http://gottcode.org/xfce4-whiskermenu-plugin/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin cmake

SRC_URI[md5sum] = "9bb98425d45b027e7a35905d07b0cbbc"
SRC_URI[sha256sum] = "20684ffe014b84d3d0630bbfcc5f8707582acb67b602ab7ef6317df6782df2b4"

RRECOMMENDS_${PN} += "menulibre"
