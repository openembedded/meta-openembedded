DESCRIPTION = "Tiny image-viewer"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/ristretto"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "libexif gtk+ dbus-glib libxfce4ui libxfce4util xfconf cairo"

inherit xfce-app

SRC_URI[md5sum] = "124c4e98f837e35c728b0ff1d9078b1a"
SRC_URI[sha256sum] = "a8bf0aa8a18af4ad8d53cbfc918bc92db51b5446cf7ff09e7b988fe5c7b986e4"
