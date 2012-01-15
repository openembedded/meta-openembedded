DESCRIPTION = "Tiny image-viewer"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/ristretto"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "libexif gtk+ dbus-glib libxfce4ui libxfce4util xfconf cairo"

inherit xfce-app

RRECOMMENDS_${PN} += "tumbler"

SRC_URI[md5sum] = "ab54fffe197c220bf26553c9a4f95658"
SRC_URI[sha256sum] = "a28c9e472c329b5f3136ebea887dec5ba93a37d5562b493633161392aff5545f"
