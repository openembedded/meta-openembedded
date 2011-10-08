DESCRIPTION = "Tiny image-viewer"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/ristretto"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "libexif gtk+ dbus-glib libxfce4ui libxfce4util xfconf cairo"

inherit xfce

# SRC_URI must follow inherited one
SRC_URI = "http://archive.xfce.org/src/apps/${PN}/${@'${PV}'[0:3]}/${PN}-${PV}.tar.bz2 \
"

SRC_URI[md5sum] = "4b0f58c00ef99f13ebf134979ee76980"
SRC_URI[sha256sum] = "8f4986a646460b24282cc95cc30f03484a5c8e0f1c568a9f4ef5749a66ec62da"
