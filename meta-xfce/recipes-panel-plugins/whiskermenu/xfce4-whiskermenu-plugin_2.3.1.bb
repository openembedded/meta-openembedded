SUMMARY = "An alternate menu for the Xfce desktop environment"
HOMEPAGE = "http://gottcode.org/xfce4-whiskermenu-plugin/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin cmake

SRC_URI[md5sum] = "ed16fcc671cb4e4853701b81fdb5dcee"
SRC_URI[sha256sum] = "22fb7277adc2e2313dfada03d69040edc3ac2c2080104cbdd9a3c47ebad0cab2"

RRECOMMENDS_${PN} += "menulibre"
