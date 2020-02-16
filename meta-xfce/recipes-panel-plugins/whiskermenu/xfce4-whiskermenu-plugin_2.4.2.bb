SUMMARY = "An alternate menu for the Xfce desktop environment"
HOMEPAGE = "http://gottcode.org/xfce4-whiskermenu-plugin/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin cmake

SRC_URI[md5sum] = "f553d3be2ffebd8d9fa5b6415647e4ff"
SRC_URI[sha256sum] = "fb2e1d44744d851c5339900e7af9068397028efa9ee5235a66ab273e95740dee"

RRECOMMENDS_${PN} += "menulibre"
