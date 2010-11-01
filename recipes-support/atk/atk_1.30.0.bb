DESCRIPTION = "An accessibility toolkit for GNOME."
SECTION = "x11/libs"
PRIORITY = "optional"
LICENSE = "LGPL"

PR = "r1"

inherit gnome

SRC_URI[archive.md5sum] = "548d413775819fef425410739041cac3"
SRC_URI[archive.sha256sum] = "92b9b1213cafc68fe9c3806273b968c26423237d7b1f631dd83dc5270b8c268c"

DEPENDS = "glib-2.0 gtk-doc-native"

EXTRA_OECONF += "--disable-glibtest"

BBCLASSEXTEND = "native"

