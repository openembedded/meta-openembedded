require libgee.inc
PE = "1"
PR = "${INC_PR}.1"
#autoreconf needs introspection.m4 (staged by gobject-introspection-native) after http://git.gnome.org/browse/libgee/commit/?id=d026a29b38ca1a3388981c6e75a92602212373d8
DEPENDS += "gobject-introspection-native"
DEPENDS_virtclass-native += "gobject-introspection-native"

SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/libgee/0.6/${BPN}-${PV}.tar.bz2"
S = "${WORKDIR}/${BPN}-${PV}"

SRC_URI[md5sum] = "4eb513b23ab6ea78884989518a4acf6f"
SRC_URI[sha256sum] = "e586678d0a88637abeaaf850b62231000772e79ea6d9c4b45dc3cea99f778a7a"
