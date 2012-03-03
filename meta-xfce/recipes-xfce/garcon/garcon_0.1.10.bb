DESCRIPTION="Xfce Menu Library"
SECTION = "x11/libs"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=04a01abcbdabffae1ca26335a293276b"
DEPENDS = "glib-2.0 xfce4-dev-tools-native libxfce4util"

inherit xfce

FILES_${PN} += " ${datadir}/desktop-directories"

SRC_URI[md5sum] = "e53514e79c4da2631dd74994452d3c96"
SRC_URI[sha256sum] = "c2497a2991101fc1e621dc712ef72aba7c238f6e5f5a1733c9572f9b23761316"
