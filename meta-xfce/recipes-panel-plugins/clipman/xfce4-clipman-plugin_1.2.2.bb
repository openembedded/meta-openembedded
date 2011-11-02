DESCRIPTION = "Clipman is a clipboard manager for Xfce"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-clipman-plugin"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

inherit xfce-panel-plugin

DEPENDS += "xfconf xproto libxtst"

SRC_URI[md5sum] = "60df7769a8271d7fcab177210f4291e8"
SRC_URI[sha256sum] = "fcac10bc150eb2f73955f28184ac2b8324c9aabc3d773242eadcec137e1096a1"
