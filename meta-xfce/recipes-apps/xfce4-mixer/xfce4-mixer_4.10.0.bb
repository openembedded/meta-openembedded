SUMMARY = "A volume control application based on GStreamer"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

inherit xfce-panel-plugin

DEPENDS += "glib-2.0 gst-plugins-base gtk+ xfconf libunique"

SRC_URI = "http://archive.xfce.org/src/apps/${BPN}/${@xfce_verdir("${PV}")}/${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "e47d5b3e873fdee3fa80d309a5f53e9c"
SRC_URI[sha256sum] = "a8c589001b438171ea5a6b46ce9895c3ffbc5081ab67805ab9870e540068dade"

RDEPENDS_${PN} = "gst-meta-audio"
