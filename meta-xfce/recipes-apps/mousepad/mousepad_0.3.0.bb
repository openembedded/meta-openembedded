DESCRIPTION = "A simple text editor for Xfce"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "gtk+ dbus-glib gtksourceview2"

inherit xfce-app

SRC_URI[md5sum] = "dcfcdfaa8a19c89f35d5f6f64753e6e1"
SRC_URI[sha256sum] = "10f27506994d0d0b8f9e02555404a144babedab97517abe3b6be8b2d21ff046d"
