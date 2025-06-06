SUMMARY = "Clipman is a clipboard manager for Xfce"
HOMEPAGE = "https://docs.xfce.org/panel-plugins/xfce4-clipman-plugin/start"
SECTION = "x11/application"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

inherit xfce-panel-plugin

DEPENDS += "xfconf xorgproto libxtst"

SRC_URI[sha256sum] = "e11c1f976217fc959cee98ecfb934058ae485cb363d2c25c7ddede44394c9a10"

PACKAGECONFIG ??= ""
PACKAGECONFIG[qrencode] = "--enable-libqrencode,--disable-libqrencode,qrencode"

FILES:${PN} += "${datadir}/metainfo"
