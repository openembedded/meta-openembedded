SUMMARY = "An alternate menu for the Xfce desktop environment"
HOMEPAGE = "http://gottcode.org/xfce4-whiskermenu-plugin/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin cmake

SRC_URI[sha256sum] = "6d20e22c18593aca5adecaf0a7a4f33a6bda233bdd92d3ca7b51c37d0baaf76e"

RRECOMMENDS:${PN} += "menulibre"
