SUMMARY = "Clipman is a clipboard manager for Xfce"
HOMEPAGE = "https://docs.xfce.org/panel-plugins/xfce4-clipman-plugin/start"
SECTION = "x11/application"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

inherit xfce-panel-plugin

DEPENDS += "xfconf xorgproto libxtst"

SRC_URI[sha256sum] = "9bae27808a50e959e0912b7202ea5d651ed7401a6fc227f811d9bdaf2e44178c"

PACKAGECONFIG ??= ""
PACKAGECONFIG[qrencode] = "--enable-libqrencode,--disable-libqrencode,qrencode"

FILES:${PN} += "${datadir}/metainfo"
