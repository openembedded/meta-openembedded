require libgee.inc
PE = "1"
PR = "${INC_PR}.2"
DEPENDS += "gobject-introspection-stub"
DEPENDS_virtclass-native += "gobject-introspection-stub-native"

SHRT_VER = "${@d.getVar('PV',1).split('.')[0]}.${@d.getVar('PV',1).split('.')[1]}"
SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/libgee/${SHRT_VER}/${BP}.tar.xz"
SRC_URI[md5sum] = "a32bf498cf33d5e3417823a7b252ad22"
SRC_URI[sha256sum] = "55f39f3b28e676f6cbd9377d83edd031084436a4da41280a9503c94faffb1665"
