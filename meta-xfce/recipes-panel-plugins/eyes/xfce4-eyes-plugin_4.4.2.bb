SUMMARY = "Panel plugin with graphical representation of the cpu frequency"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-eyes-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin

SRC_URI[md5sum] = "1ef352c68dd8929c9e8743200b758b3c"
SRC_URI[sha256sum] = "dad102fedd0d9e6df45338a018387b2aacaf24b05eb798275be0dfc05ffa3021"

FILES_${PN} += "${datadir}/xfce4/eyes"
