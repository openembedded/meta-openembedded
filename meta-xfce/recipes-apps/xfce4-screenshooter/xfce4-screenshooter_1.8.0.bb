DESCRIPTION = "Application to take screenshots"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/xfce4-screenshooter"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d791728a073bc009b4ffaf00b7599855"
DEPENDS = "xfce4-panel libxfce4util libxfce4ui gdk-pixbuf gtk+ glib-2.0 libsoup-2.4 exo libxfixes xext virtual/libx11"

PR = "r0"

inherit xfce-app

FILES_${PN} += "${datadir}/xfce4/panel-plugins/*"
FILES_${PN}-dbg += "${libexecdir}/xfce4/panel-plugins/.debug"

SRC_URI[md5sum] = "b51ab5725418e7258273c4a6402adb02"
SRC_URI[sha256sum] = "68748a42ae68c5a8e9ed1c14ec5c741a344ab30b5b325b8812220539548ad83e"
