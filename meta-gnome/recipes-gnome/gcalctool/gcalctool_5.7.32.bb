LICENSE = "GPL"
SECTION = "x11"
DEPENDS = "gtk+"
DESCRIPTION = "gcalctool is a powerful calculator"
PR = "r2"

SRC_URI = "http://download.gnome.org/sources/${PN}/5.7/${PN}-${PV}.tar.gz \
        file://makefile-fix.diff;patch=1\
	file://fix-includedir.patch;patch=1"

inherit autotools pkgconfig

EXTRA_OECONF = "--disable-gnome"
