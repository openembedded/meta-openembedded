DESCRIPTION = "Panel plugin displaying date and time and a calendar when left-clicked"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-datetime-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

inherit xfce-panel-plugin

SRCREV = "97ebbac9ba52397ec34edb43935083f6263d1666"
PV = "0.6.1+gitr${SRCPV}"
S = "${WORKDIR}/git"

SRC_URI = "git://git.xfce.org/panel-plugins/xfce4-datetime-plugin;protocol=git;branch=master \
           file://0001-port-to-libxfce4ui.patch \
           file://0002-configure.ac.in-remove-breaking-BM_DEBUG_SUPPORT.patch \
          "
SRC_URI[md5sum] = "e82f51ff0e75a63e5cbd139e43e094f9"
SRC_URI[sha256sum] = "fb340c1c2170d4f33c7f278772966f3c01caaedcd4a7f58f670bf8e28580bb1b"

do_configure_prepend() {
	NOCONFIGURE=yes ./autogen.sh
}
