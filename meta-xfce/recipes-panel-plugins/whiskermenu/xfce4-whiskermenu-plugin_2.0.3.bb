SUMMARY = "An alternate menu for the Xfce desktop environment"
HOMEPAGE = "http://gottcode.org/xfce4-whiskermenu-plugin/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin cmake

SRC_URI[md5sum] = "3cbbe6f233089b6923113f47d311f875"
SRC_URI[sha256sum] = "1b7afb233c74c33f0ee26de6514c4168497f4aa584dc2f6778dff15d8d47b165"

RRECOMMENDS_${PN} += "menulibre"
