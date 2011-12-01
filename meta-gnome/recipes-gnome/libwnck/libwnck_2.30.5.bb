DESCRIPTION = "Window navigation construction toolkit"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2"

PR = "r1"

SECTION = "x11/libs"
DEPENDS = "gobject-introspection-native gtk+ gdk-pixbuf-native"

inherit gnome
SRC_URI[archive.md5sum] = "4162d5b96151e6d24ec02ae3a822203c"
SRC_URI[archive.sha256sum] = "56b6681e89cd45491bb640165d62276d81369a08974042b26645dc1e0e954cc1"

EXTRA_OECONF += "--enable-introspection=no"

do_configure_prepend() {
	sed -i '/GOBJECT_INTROSPECTION_CHECK([0.6.14])/d' configure.ac
}
