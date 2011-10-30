DESCRIPTION = "Tiny image-viewer"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/ristretto"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "libexif gtk+ dbus-glib libxfce4ui libxfce4util xfconf cairo"

inherit xfce-app

SRC_URI[md5sum] = "ee812d1200674960c246a7e05a1cbc6d"
SRC_URI[sha256sum] = "c43832d7e6e37b670c3dd04dcf406266ff5980dc80abed7b8a82aa7c7defaa69"
