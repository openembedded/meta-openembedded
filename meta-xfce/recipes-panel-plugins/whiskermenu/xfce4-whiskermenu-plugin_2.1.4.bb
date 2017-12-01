SUMMARY = "An alternate menu for the Xfce desktop environment"
HOMEPAGE = "http://gottcode.org/xfce4-whiskermenu-plugin/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin cmake

SRC_URI[md5sum] = "5aeff066c38fda9aea2a2a3d8ab6dc9e"
SRC_URI[sha256sum] = "ffdf2d5d4e0eeffc96d4642fdce8c55f26d3444cbd6c7b3d6f6cd7168ad4a6d5"

RRECOMMENDS_${PN} += "menulibre"
