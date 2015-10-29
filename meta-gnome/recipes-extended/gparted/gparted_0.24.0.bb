SUMMARY = "A partition editor to graphically manage disk partitions "
HOMEPAGE = "http://gparted.org/index.php"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit autotools pkgconfig

SRC_URI = "${SOURCEFORGE_MIRROR}/project/${BPN}/${BPN}/${BPN}-${PV}/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "b8480274c68876acff5965d4346710e7"
SRC_URI[sha256sum] = "02398ab33894a59b0bd8707e598c46d8bb56f1413cd54de48eed61e2920ecd60"

DEPENDS = "glib-2.0 gtkmm parted gnome-doc-utils-native"

EXTRA_OECONF = "--disable-scrollkeeper --disable-doc"

FILES_${PN} += "${datadir}/icons ${datadir}/appdata"

RDEPENDS_${PN} = "dosfstools mtools e2fsprogs"
