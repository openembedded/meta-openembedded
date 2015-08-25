SUMMARY = "An alternate menu for the Xfce desktop environment"
HOMEPAGE = "http://gottcode.org/xfce4-whiskermenu-plugin/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin cmake

SRC_URI = " \
    git://git.xfce.org/panel-plugins/xfce4-whiskermenu-plugin \
"

SRCREV = "2d3c03eacd78051d2ef8ee3b6a4e2bff4753680e"
PV = "1.5.0+gitr${SRCPV}"
S = "${WORKDIR}/git"
