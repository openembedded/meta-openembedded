DESCRIPTION = "XKB layout switching panel plug-in for the Xfce desktop environment"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-xkb-plugin"
SECTION = "x11/application"
LICENSE = "Alexander_Iliev"
LIC_FILES_CHKSUM = "file://COPYING;md5=496f09f084b0f7e6f02f769a84490c6b"

inherit xfce-panel-plugin

DEPENDS += "libxklavier libwnck librsvg garcon"

PV = "0.5.4.3+gitr${SRCPV}"

SRC_URI = "git://git.xfce.org/panel-plugins/xfce4-xkb-plugin;protocol=git;branch=master"
SRCREV = "b98b82d8522842e38a6355f9144f8348a6ab2aeb"
S = "${WORKDIR}/git"

do_configure_prepend() {
    NOCONFIGURE=yes ./autogen.sh
}

FILES_${PN} += "${datadir}/xfce4/xkb"

RDEPENDS_${PN} = "xfce4-settings"
