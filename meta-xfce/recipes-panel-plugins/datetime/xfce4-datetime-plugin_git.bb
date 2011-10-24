DESCRIPTION = "Panel plugin displaying date and time and a calendar when left-clicked"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-datetime-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

inherit xfce-panel-plugin

SRCREV = "e268210db6a32b2a47c03c63e5908ee3ba9461cc"
PV = "0.6.1+gitr${SRCPV}"
S = "${WORKDIR}/git"

SRC_URI = "git://git.xfce.org/panel-plugins/xfce4-datetime-plugin;protocol=git;branch=master \
           file://port-to-libxfce4ui.patch \
          "
SRC_URI[md5sum] = "e82f51ff0e75a63e5cbd139e43e094f9"
SRC_URI[sha256sum] = "fb340c1c2170d4f33c7f278772966f3c01caaedcd4a7f58f670bf8e28580bb1b"

do_configure_prepend() {
	NOCONFIGURE=yes ./autogen.sh
}
