SUMMARY = "XKB layout switching panel plug-in for the Xfce desktop environment"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-xkb-plugin"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=496f09f084b0f7e6f02f769a84490c6b"

inherit xfce-panel-plugin

SRC_URI[md5sum] = "7fbc3d8c84d6662d819dd1803f0fee34"
SRC_URI[sha256sum] = "28367c309863581ae4de529b35bdedf5a1c918096a9a2e6e771554cba7bf5a6a"

DEPENDS += "libxklavier libwnck librsvg garcon"

FILES_${PN} += "${datadir}/xfce4/xkb"

RDEPENDS_${PN} = "xfce4-settings"
