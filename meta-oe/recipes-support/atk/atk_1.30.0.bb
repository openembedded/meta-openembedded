DESCRIPTION = "An accessibility toolkit for GNOME."
SECTION = "x11/libs"
PRIORITY = "optional"
LICENSE = "LGPLv2 & LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7 \
                    file://atk/atkutil.c;endline=20;md5=db21b0bdbef9da4dc6eb122debc9f9bc \
                    file://atk/atk.h;endline=20;md5=c58238d688c24387376d6c69d06248a7
PR = "r1"

inherit gnome

SRC_URI[archive.md5sum] = "548d413775819fef425410739041cac3"
SRC_URI[archive.sha256sum] = "92b9b1213cafc68fe9c3806273b968c26423237d7b1f631dd83dc5270b8c268c"

DEPENDS = "glib-2.0 gtk-doc-native"

EXTRA_OECONF += "--disable-glibtest"

BBCLASSEXTEND = "native"

