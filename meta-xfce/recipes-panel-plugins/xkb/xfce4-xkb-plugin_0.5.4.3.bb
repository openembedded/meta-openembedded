DESCRIPTION = "XKB layout switching panel plug-in for the Xfce desktop environment"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-xkb-plugin"
SECTION = "x11/application"
LICENSE = "Alexander_Iliev"
LIC_FILES_CHKSUM = "file://COPYING;md5=496f09f084b0f7e6f02f769a84490c6b"

inherit xfce-panel-plugin

DEPENDS += "libxklavier libwnck librsvg"

SRC_URI[md5sum] = "b31144bd50875ec73f0b3101456c97fd"
SRC_URI[sha256sum] = "ca4801bb2edfe04eeceb71879b3cf79a0674e8311c39117efdb9d3521307396d"

FILES_${PN} += "${datadir}/xfce4/xkb"
