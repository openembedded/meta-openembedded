require recipes-devtools/vala/vala.inc

PR = "r1"

DEPENDS += "gtk+"

SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/vala/0.14/vala-${PV}.tar.xz \
           file://0001-git-version-gen-don-t-append-dirty-if-we-re-not-in-g.patch \
"

FILES_${PN} += "${datadir}/vala-0.14/vapi"

SRC_URI[md5sum] = "f05263a56c1e328637e4c97a61befdad"
SRC_URI[sha256sum] = "807e6978484f66cab3cf4fefd72f37a4293d2831d7a5fd29119bdf9d38a5b3d0"
