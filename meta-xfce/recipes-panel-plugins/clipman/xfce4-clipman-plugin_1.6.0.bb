SUMMARY = "Clipman is a clipboard manager for Xfce"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-clipman-plugin"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

inherit xfce-panel-plugin

DEPENDS += "xfconf xorgproto libxtst"

SRC_URI[md5sum] = "6db8e1b6ed2babab339a650c8f6d596b"
SRC_URI[sha256sum] = "0295da92ade7d6e1d8b15a525e73ddcfba1986142d34c6143955164a13552ed7"

PACKAGECONFIG ??= ""
PACKAGECONFIG[qrencode] = "--enable-libqrencode,--disable-libqrencode,qrencode"

FILES_${PN} += "${datadir}/appdata"
