DESCRIPTION = "C++ bindings for the pango library."
SECTION = "libs"
PRIORITY = "optional"
LICENSE = "LGPL"
DEPENDS = "mm-common cairomm glibmm"
SHRT_VER = "${@bb.data.getVar('PV',d,1).split('.')[0]}.${@bb.data.getVar('PV',d,1).split('.')[1]}"
PR = "r1"

SRC_URI = "ftp://ftp.gnome.org/pub/GNOME/sources/pangomm/${SHRT_VER}/pangomm-${PV}.tar.bz2"

inherit autotools flow-lossage

FILES_${PN} = "${libdir}/lib*.so.*"
FILES_${PN}-dev += "${libdir}/*/include/"

EXTRA_OECONF = " --disable-documentation "

AUTOTOOLS_STAGE_PKGCONFIG = "1"


SRC_URI[md5sum] = "37f54dc8e6cb73ed923b22f313352156"
SRC_URI[sha256sum] = "bf26ebe42c12c81e5c32ceca80ff226a01c8d80d4db2a4cc3463d5bf241b095e"
