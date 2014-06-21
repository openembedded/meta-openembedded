DESCRIPTION="Xfce Menu Library"
SECTION = "x11/libs"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=04a01abcbdabffae1ca26335a293276b"
DEPENDS = "glib-2.0 xfce4-dev-tools-native libxfce4util intltool-native"
PR = "r1"

inherit xfce gtk-doc

SRC_URI += "file://0001-xfce-applications.menu-don-t-bloat-settings-menu-by-.patch"
SRC_URI[md5sum] = "c3cf89c836be0ddb281c81e4808fb68b"
SRC_URI[sha256sum] = "48b644b8b2ffe597974e2526ca1a5d2d7da6a09c2d434f008dec80e9152701f7"

FILES_${PN} += "${datadir}/desktop-directories"
