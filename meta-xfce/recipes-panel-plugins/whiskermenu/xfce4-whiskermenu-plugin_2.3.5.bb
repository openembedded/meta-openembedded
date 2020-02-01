SUMMARY = "An alternate menu for the Xfce desktop environment"
HOMEPAGE = "http://gottcode.org/xfce4-whiskermenu-plugin/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin cmake

SRC_URI[md5sum] = "b5242d7922fa4253cc99c253efcff7cf"
SRC_URI[sha256sum] = "8f7134f4c1b5ff5290048c5b96b130e1238a286002cbe35255c2cd7d5a1ab6b4"

RRECOMMENDS_${PN} += "menulibre"
