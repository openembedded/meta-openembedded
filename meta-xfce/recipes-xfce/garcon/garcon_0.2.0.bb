DESCRIPTION="Xfce Menu Library"
SECTION = "x11/libs"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=04a01abcbdabffae1ca26335a293276b"
DEPENDS = "glib-2.0 xfce4-dev-tools-native libxfce4util"

inherit xfce

SRC_URI[md5sum] = "301e7b8015060dd30407b68dd8c4bdb7"
SRC_URI[sha256sum] = "02dea3edb9c0039eca4748e964c61b0e1cc10f2d7f9ce0c837287ac5fa9ef76c"

FILES_${PN} += "${datadir}/desktop-directories"
