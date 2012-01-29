DESCRIPTION = "Tiny image-viewer"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/ristretto"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "libexif gtk+ dbus-glib libxfce4ui libxfce4util xfconf cairo"

inherit xfce-app

RRECOMMENDS_${PN} += "tumbler"

SRC_URI[md5sum] = "86c042ee4ffefb0ff5e806849ec4d33c"
SRC_URI[sha256sum] = "e2ed5c6bc3f56cf82942c54d1492e54607ccf073c7963c1df5fa2cd3a9a71a32"
