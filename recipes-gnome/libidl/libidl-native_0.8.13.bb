SECTION = "libs"
require libidl_${PV}.bb
inherit native

PR = "r1"

DEPENDS = "bison-native glib-2.0-native"

SRC_URI[md5sum] = "b43b289a859eb38a710f70622c46e571"
SRC_URI[sha256sum] = "bccc7e10dae979518ff012f8464e47ec4b3558a5456a94c8679653aa0b262b71"
