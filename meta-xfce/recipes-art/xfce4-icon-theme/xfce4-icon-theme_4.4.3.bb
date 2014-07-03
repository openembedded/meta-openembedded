SUMMARY = "xfce4 icon theme rodent"
SECTION = "x11/wm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "intltool-native xfce4-dev-tools-native"

inherit xfce

SRC_URI = "http://archive.xfce.org/src/art/${BPN}/${@'${PV}'[0:3]}/${BP}.tar.bz2"
SRC_URI[md5sum] = "73ce2977b84f634a6a6c5d9c27e336db"
SRC_URI[sha256sum] = "d4786692d56b8a563f66a5f260f1f13ade11e86c78cbcb25a9f9a5fc47cf66fa"

FILES_${PN} += "${datadir}/xfce4/mime"
