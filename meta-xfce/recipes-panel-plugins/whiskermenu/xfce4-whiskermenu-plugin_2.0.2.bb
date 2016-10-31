SUMMARY = "An alternate menu for the Xfce desktop environment"
HOMEPAGE = "http://gottcode.org/xfce4-whiskermenu-plugin/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin cmake

SRC_URI[md5sum] = "abaae8068fdcca9e72a41f4a6d28c685"
SRC_URI[sha256sum] = "c8ba09b3cc0b2ec863af310752a9ca69560fc4c27cbd3174a9dceca1af79e412"

RRECOMMENDS_${PN} += "menulibre"
