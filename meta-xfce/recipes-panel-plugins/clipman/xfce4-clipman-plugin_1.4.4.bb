SUMMARY = "Clipman is a clipboard manager for Xfce"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-clipman-plugin"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

inherit xfce-panel-plugin

DEPENDS += "xfconf xorgproto libxtst"

SRC_URI[md5sum] = "104c917ef53a66f7aa2dca01b43c3b77"
SRC_URI[sha256sum] = "08e14c5d0fcee9adb4bc77efc0ab4af2b12b3fe1ee5e2121fc60e877ac9c29a0"

PACKAGECONFIG ??= ""
PACKAGECONFIG[qrencode] = "--enable-libqrencode,--disable-libqrencode,qrencode"

FILES_${PN} += "${datadir}/appdata"
