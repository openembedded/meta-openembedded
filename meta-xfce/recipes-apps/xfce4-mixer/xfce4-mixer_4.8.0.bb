DESCRIPTION = "A volume control application based on GStreamer"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

inherit xfce-panel-plugin

DEPENDS += "glib-2.0 gst-plugins-base gtk+ xfconf"

SRC_URI = "http://archive.xfce.org/src/apps/${BPN}/${@'${PV}'[0:3]}/${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "3fa3a9973e18c04da26709a654e242ff"
SRC_URI[sha256sum] = "9f9f48fdd0d3f6b1e46694b93a44d15b800db72a96d831b5111d9ac976970fab"

RDEPENDS_${PN} = "gst-meta-audio"
