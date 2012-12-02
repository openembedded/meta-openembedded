LICENSE = "GPL"
SECTION = "x11"
DEPENDS = "gtk+ gnome-doc-utils"
DESCRIPTION = "gcalctool is a powerful calculator"
PR = "r0"

SRC_URI = "http://download.gnome.org/sources/${PN}/5.8/${PN}-${PV}.tar.gz \
	file://fix-includedir.patch"

SRC_URI[md5sum] = "dd9d4f326d7d925a5ad9fbb1c5b32142"
SRC_URI[sha256sum] = "8806e7696eeee62f2df21135fc488ba99f7d55dfd2a40bb9615e2edd95391589"

inherit autotools pkgconfig

EXTRA_OECONF = "--disable-gnome"
