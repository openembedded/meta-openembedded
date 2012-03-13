DESCRIPTION = "Tiny image-viewer"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/ristretto"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "libexif gtk+ dbus-glib libxfce4ui libxfce4util xfconf cairo"

inherit xfce-app

RRECOMMENDS_${PN} += "tumbler"

SRC_URI[md5sum] = "3bfdf129926236a45c58d7afdbb703b7"
SRC_URI[sha256sum] = "96dc519c2e930955f97027d7b9bb7542b01a6ad0ca53a16f9af58f7c851eb8f2"
