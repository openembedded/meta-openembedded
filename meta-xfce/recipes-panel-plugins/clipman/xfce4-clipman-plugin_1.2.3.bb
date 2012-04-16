DESCRIPTION = "Clipman is a clipboard manager for Xfce"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-clipman-plugin"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

inherit xfce-panel-plugin

DEPENDS += "xfconf xproto libxtst"

SRC_URI[md5sum] = "61f3be97efa379cb358980c94e14692a"
SRC_URI[sha256sum] = "4424447067cb2c3972c375330d2e1d19f12e59c41bd03e111c2e6e9e174a5067"
