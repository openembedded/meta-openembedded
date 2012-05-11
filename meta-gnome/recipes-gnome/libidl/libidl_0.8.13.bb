require libidl.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=55ca817ccb7d5b5b66355690e9abc605"

DEPENDS = "glib-2.0 flex-native libidl-native"

PR = "r1"

BINCONFIG_GLOB = "*-config-2"
inherit autotools pkgconfig binconfig

SRC_URI[md5sum] = "b43b289a859eb38a710f70622c46e571"
SRC_URI[sha256sum] = "bccc7e10dae979518ff012f8464e47ec4b3558a5456a94c8679653aa0b262b71"
