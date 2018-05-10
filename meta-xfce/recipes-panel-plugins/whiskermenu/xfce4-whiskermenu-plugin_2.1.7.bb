SUMMARY = "An alternate menu for the Xfce desktop environment"
HOMEPAGE = "http://gottcode.org/xfce4-whiskermenu-plugin/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin cmake

SRC_URI[md5sum] = "5cdaaf5799c3f976e8cee81ed24c7fe7"
SRC_URI[sha256sum] = "045c48bbe6fba62a46c2752a9eb3533f03bdd29dd23af29208622b79eb045e39"

RRECOMMENDS_${PN} += "menulibre"
